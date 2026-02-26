ALTER TABLE product
    ADD CONSTRAINT uk_product_sku UNIQUE (sku);

CREATE INDEX idx_product_name ON product(name);
