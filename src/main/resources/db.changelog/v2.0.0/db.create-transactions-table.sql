CREATE TABLE IF NOT EXISTS transactions(
    id UUID NOT NULL,
    title CHARACTER VARYING(30) NOT NULL,
    type CHARACTER VARYING(10) NOT NULL,
    category UUID NOT NULL,
    finance_institution UUID NOT NULL,
    date DATE NOT NULL,
    value NUMERIC(10, 2) NOT NULL,

    PRIMARY KEY(id),

    CONSTRAINT fk_id_categories
        FOREIGN KEY (category)
        REFERENCES categories(id),

    CONSTRAINT fk_id_finance_institution
        FOREIGN KEY (finance_institution)
        REFERENCES finance_institutions(id)
);