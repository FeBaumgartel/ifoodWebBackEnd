CREATE TABLE restaurants(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    image TEXT,
    street TEXT NOT NULL,
    number TEXT NOT NULL,
    city TEXT NOT NULL
)