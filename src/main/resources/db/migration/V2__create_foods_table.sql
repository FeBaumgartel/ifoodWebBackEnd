CREATE TABLE foods(
    id BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    image TEXT,
    price DECIMAL NOT NULL,
    restaurant_id BIGINT NOT NULL,
    CONSTRAINT fk_restaurant FOREIGN KEY(restaurant_id) REFERENCES restaurants(id)
)