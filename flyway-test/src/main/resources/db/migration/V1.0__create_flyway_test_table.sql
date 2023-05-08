CREATE TABLE flyway_test_table (
   engine_name VARCHAR(64) NOT NULL COLLATE 'utf8_general_ci',
   device_type INT(11) NOT NULL,
   cost_name VARCHAR(64) NOT NULL,
   cost_value FLOAT NULL DEFAULT NULL,
   last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   comment VARCHAR(1024) NULL DEFAULT NULL,
   PRIMARY KEY (engine_name) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
