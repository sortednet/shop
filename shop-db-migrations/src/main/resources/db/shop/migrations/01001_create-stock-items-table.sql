CREATE TABLE stock_item 
(
    id	NUMERIC(20) NOT NULL,
    sku VARCHAR(128) NOT NULL,
    short_description VARCHAR(256) NOT NULL,
    full_description VARCHAR(2048) NOT NULL

);

ALTER TABLE stock_item ADD CONSTRAINT pk_stock_item
    PRIMARY KEY (id) ;



GRANT SELECT, INSERT, UPDATE	ON stock_item TO flowfx_app_fullaccess_role;
GRANT SELECT					ON stock_item TO flowfx_support_select_role;
