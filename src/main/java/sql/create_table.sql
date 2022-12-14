CREATE TYPE CustomerType AS ENUM ('VIP', 'NORMAL');
CREATE TYPE ProductType AS ENUM ('ELECTRONIC_APPLIANCES', 'READABLE', 'SHOES');
CREATE TYPE DisplayType AS ENUM ('LCD', 'LED', 'OLED', 'QLED');
CREATE TYPE ShoesType AS ENUM ('SPORT', 'FORMAL', 'SLIPPERS');
CREATE TYPE BookType AS ENUM ('BOOK', 'MAGAZINE');
CREATE TYPE BookSubject AS ENUM ('ACTION', 'ADVENTURE', 'COMIC','HORROR', 'FANTASY', 'HISTORICAL');
CREATE TYPE Color AS ENUM ('BLUE', 'RED', 'PURPLE','YELLOW', 'BLACK', 'WHITE');
CREATE TYPE CartStatus AS ENUM ('PAID', 'UNPAID', 'CANCEL');

create table if not exists person
(
    id            bigserial primary key not null,
    firstname     varchar(255)          not null,
    lastname      varchar(255)          not null,
    national_code varchar(255) unique   not null
);

create table if not exists admin
(
    id        bigserial primary key not null,
    username  varchar(20) unique    not null,
    password  varchar(30)           not null,
    person_id int8 references Person (id)
);

create table if not exists customer
(
    id            bigserial primary key not null,
    username      varchar(20) unique    not null,
    password      varchar(30)           not null,
    customer_type CustomerType          not null,
    person_id     int8 references Person (id)
);

create table if not exists seller
(
    id           bigserial primary key not null,
    username     varchar(20) unique    not null,
    password     varchar(30)           not null,
    product_type ProductType           not null,
    company      varchar(255)          not null,
    person_id    int8 references Person (id)
);

create table if not exists product
(
    id           serial primary key not null,
    product_type ProductType        not null,
    price        float              not null,
    description  text               not null,
    seller_id    int8 references Seller (id),
    quantity     int                not null
);

create table if not exists tv
(
    id           serial primary key not null,
    display_type displayType        not null,
    inch         int                not null,
    product_id   int references product (id)
);

create table if not exists radio
(
    id                 serial primary key not null,
    is_cd_player       boolean            not null,
    is_cassette_player boolean            not null,
    is_flash_player    boolean            not null,
    product_id         int references product (id)
);

create table if not exists book
(
    id              serial primary key not null,
    book_type       BookType           not null,
    book_subject    BookSubject        not null,
    number_of_pages int                not null,
    author_name     varchar(255)       not null,
    publisher_name  varchar(255)       not null,
    product_id      int references product (id)
);

create table if not exists shoes
(
    id         serial primary key not null,
    size       int[]              not null,
    main_color Color              not null,
    type       ShoesType          not null,
    product_id int references product (id)
);

create table if not exists cart
(
    id bigserial primary key not null ,
    customer_id int references customer(id),
    cart_status CartStatus not null
);

create table if not exists cart_products
(
    id bigserial primary key not null ,
    product_id int references product(id),
    cart_id int8 references cart(id),
    quantity int not null,
    description text not null,
    price float not null
);

create table if not exists choose_size_shoes
(
    id bigserial primary key not null,
    size int not null,
    cart_products_id int8 references cart_products(id)
);