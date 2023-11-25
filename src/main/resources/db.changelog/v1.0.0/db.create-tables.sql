CREATE TABLE IF NOT EXISTS users(
    id UUID NOT NULL,
    name TEXT NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password CHAR(60) NOT NULL,
    role TEXT NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS categories(
    id UUID NOT NULL,
    username TEXT NOT NULL,
    name TEXT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_username_categories
        FOREIGN KEY (username)
        REFERENCES users(login)
);

CREATE TABLE IF NOT EXISTS finance_institutions(
    id UUID NOT NULL,
    username TEXT NOT NULL,
    name TEXT NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_username_finance_institutions
        FOREIGN KEY (username)
        REFERENCES users(login)
);
