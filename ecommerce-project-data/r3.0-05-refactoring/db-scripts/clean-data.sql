USE `full-stack-ecommerce`;

--
-- Clean up previous database tables
--
SET FOREIGN_KEY_CHECKS=0;

TRUNCATE `customer`;
TRUNCATE `order_item`;
TRUNCATE `orders`;
TRUNCATE `address`;

SET FOREIGN_KEY_CHECKS=1;

--
-- Step 2: make email address unique
--
ALTER TABLE `customer` ADD UNIQUE (email);


