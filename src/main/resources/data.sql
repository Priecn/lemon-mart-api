INSERT INTO USER (ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (1, 'admin', '{bcrypt}$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'manager', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (2, 'user', '{bcrypt}$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'user@user.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (5, 'john', '{bcrypt}$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'John', 'Doe', 'john@john.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (3, 'clerk', '{bcrypt}$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'clerk', 'clerk', 'clerk@clerk.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, ENABLED, LAST_PASSWORD_RESET_DATE) VALUES (4, 'cashier', '{bcrypt}$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'cashier', 'cashier', 'cashier@cashier.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_CLERK');
INSERT INTO AUTHORITY (ID, NAME) VALUES (3, 'ROLE_CASHIER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (4, 'ROLE_MANAGER');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 4);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 3);

INSERT INTO CATEGORY (ID, NAME, TAX_RATE) VALUES (1, 'food', 5);
INSERT INTO CATEGORY (ID, NAME, TAX_RATE) VALUES (2, 'drink', 10);

INSERT INTO INVENTORY (ID, QUANTITY, CURRENT_AMOUNT) VALUES (1, 10, 8);
INSERT INTO INVENTORY (ID, QUANTITY, CURRENT_AMOUNT) VALUES (2, 20, 18);
INSERT INTO INVENTORY (ID, QUANTITY, CURRENT_AMOUNT) VALUES (3, 10, 5);

INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (1, 'PANEER', 1, 1001, 2001, 24.5, 5, TRUE, 1);
INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (2, 'AALOO', 1, 1002, 2002, 4.5, 9, FALSE, 2);
INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (3, 'CHICKEN', 1, 1003, 2003, 124.5, 5, TRUE, 1);
INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (4, 'ROTI', 1, 1004, 2004, 10, 7, TRUE, 3);
INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (5, 'COFFEE', 2, 1005, 2005, 25, 2, TRUE, 3);
INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (6, 'TEA', 2, 1006, 2006, 20.75, 5, TRUE, 2);
INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (7, 'WATER', 2, 1007, 2007, 51, 4, TRUE, 2);
INSERT INTO PRODUCT (ID, NAME, CATEGORY_ID, SKU, UPC, PRICE, DISCOUNT_MODIFIER, LISTING_STATUS, INVENTORY_ID) VALUES (8, 'RICE', 1, 1008, 2008, 30, 0, TRUE, 1);

INSERT INTO RECEIPT (ID, CASHIER_ID, BUYER_ID, MANAGER_ID, SUB_TOTAL, TAX, TOTAL, PAYMENT_TYPE, PAYMENT_AMOUNT) VALUES (1, 4, 2, 1, 100, 10, 110, 'CARD', 110);
INSERT INTO RECEIPT (ID, CASHIER_ID, BUYER_ID, MANAGER_ID, SUB_TOTAL, TAX, TOTAL, PAYMENT_TYPE, PAYMENT_AMOUNT) VALUES (2, 4, 5, 1, 200, 20, 280, 'CASH', 280);

INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (1, 2);
INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (1, 5);
INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (1, 3);
INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (2, 1);
INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (2, 6);
INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (2, 7);
INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (2, 8);
INSERT INTO RECEIPT_PRODUCT (RECEIPT_ID, PRODUCT_ID) VALUES (2, 3);