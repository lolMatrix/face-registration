CREATE TABLE file
(
    id      uuid  NOT NULL PRIMARY KEY,
    content bytea NOT NULL
);

CREATE TABLE "user"
(
    id            uuid         NOT NULL PRIMARY KEY,
    username      varchar(100) NOT NULL,
    password      varchar(255) NOT NULL,
    is_image_load bool         NOT NULL DEFAULT FALSE,
    user_image_id uuid,

    constraint user_image_id_fkey FOREIGN KEY (user_image_id) REFERENCES file (id)
);

