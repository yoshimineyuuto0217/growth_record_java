# 学習記録表 ER図

```mermaid
erDiagram
  direction TB

  USER {
    int user_id PK
    string email
    string name
    string profileimage
    string self_introduction
    string password
    datetime createdAt
    datetime updatedAt
  }

  ARTICLES {
    int article_id PK
    int user_id FK
    text article_title
    datetime createdAt
    datetime updatedAt
  }

  SAVED_ITEMS {
    int saved_item_id PK
    int user_id FK
    int article_id FK
    datetime createdAt
  }

  LIKED_ITEMS {
    int liked_item_id PK
    int user_id FK
    int article_id FK
    datetime createdAt
    datetime createdBy
  }

  TROUBLE {
    int trouble_id PK
    int user_id FK
    text trouble_content
    datetime createdAt
  }

  ARTICLE_TAGS {
    int article_id FK
    int tag_id FK
    datetime createdAt
  }

  ARTICLE_SECTIONS {
    int article_section_id PK
    int article_id FK
    int sort_order
    string section_type
    text section_content
    datetime createdAt
    datetime createdBy
  }

  TAGS {
    int tag_id PK
    string tag_name UK
    datetime createdAt
  }

  USER ||--o{ ARTICLES : "writes"
  USER ||--o{ SAVED_ITEMS : "saves"
  USER ||--o{ LIKED_ITEMS : "likes"
  USER ||--o{ TROUBLE : "reports"
  ARTICLES ||--o{ SAVED_ITEMS : "is_saved"
  ARTICLES ||--o{ LIKED_ITEMS : "is_liked"
  ARTICLES ||--o{ ARTICLE_TAGS : "has"
  ARTICLES ||--o{ ARTICLE_SECTIONS : "has"
  TAGS ||--o{ ARTICLE_TAGS : "tagged"
