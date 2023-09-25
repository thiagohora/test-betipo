create table real_estate_agencies (
    id bigint(19) not null auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    reference varchar(50) not null,
    nif varchar(20) not null,
    status varchar(30) not null,
    address_street varchar(255) not null,
    address_number varchar(255) not null,
    address_floor varchar(10) not null,
    address_door varchar(10) not null,
    address_postal_code varchar(255) not null,
    address_city varchar(255) not null,
    address_province varchar(255) not null,
    address_country varchar(255) not null,
    address_latitude double not null,
    address_longitude double not null,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 DEFAULT COLLATE utf8_unicode_ci;
