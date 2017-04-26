CREATE TABLE `ARTICLE` (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    CONSTRAINT pk_article_id PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `TAG` (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    content VARCHAR(50) NOT NULL,
    CONSTRAINT pk_tag_id PRIMARY KEY (id),
    CONSTRAINT uk_tag_content UNIQUE KEY (content)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ARTICLE_TAGS` (
    article_id BIGINT(20) NOT NULL,
    tag_id BIGINT(20) NOT NULL,
    CONSTRAINT pk_article_tags_id PRIMARY KEY (article_id, tag_id),
    CONSTRAINT fk_article_tags_article_id FOREIGN KEY (article_id) REFERENCES ARTICLE(id),
    CONSTRAINT fk_article_tags_tag_id FOREIGN KEY (tag_id) REFERENCES TAG(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;