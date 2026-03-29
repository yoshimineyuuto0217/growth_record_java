package com.jobs.growth_record_java.security.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jobs.growth_record_java.security.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                // JWTから email を取得（署名・期限も検証）
                String email = jwtService.extractEmail(token);

                if (email != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                    UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                            email,      // principal
                            null,       // credentials
                            List.of()   // authorities（今は空でOK）
                        );

                    SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
                }

            } catch (Exception e) {
                // 不正なトークンの場合は何もしない（401は後段で）
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}

// 記事投稿するAPIの繋ぎこみを行う時に使う
// package com.jobs.growth_record_java.security.filter;

// import java.io.IOException;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.jobs.growth_record_java.security.CustomUserDetails;
// import com.jobs.growth_record_java.security.service.JwtService;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final JwtService jwtService;

//     public JwtAuthenticationFilter(JwtService jwtService) {
//         this.jwtService = jwtService;
//     }

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {

//         String header = request.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {

//             String token = header.substring(7);

//             try {
//                 // JWTから情報を取得（署名・期限も内部で検証）
//                 String email = jwtService.extractEmail(token);
//                 Long userId = jwtService.extractUserId(token);

//                 if (email != null &&
//                     userId != null &&
//                     SecurityContextHolder.getContext().getAuthentication() == null) {

//                     // ★ ここが重要：principal に CustomUserDetails を入れる
//                     CustomUserDetails userDetails =
//                             new CustomUserDetails(userId, email, null);

//                     UsernamePasswordAuthenticationToken authentication =
//                             new UsernamePasswordAuthenticationToken(
//                                     userDetails,
//                                     null,
//                                     userDetails.getAuthorities()
//                             );

//                     SecurityContextHolder.getContext().setAuthentication(authentication);
//                 }

//             } catch (Exception e) {
//                 // 不正なトークン
//                 SecurityContextHolder.clearContext();
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }