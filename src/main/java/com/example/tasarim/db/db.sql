-- Create the connection_details table
CREATE TABLE connection_details (
                                    id SERIAL PRIMARY KEY,
                                    host VARCHAR(255),
                                    port INT,
                                    username VARCHAR(255),
                                    password VARCHAR(255),
                                    client_id VARCHAR(255),
                                    connection_timeout INT,
                                    keep_alive_interval INT
);

-- Create the mqtt_data table
CREATE TABLE mqtt_data (
                           id SERIAL PRIMARY KEY,
                           connection_id INT,
                           topic VARCHAR(255),
                           message TEXT,
                           host_name VARCHAR(255)
);

-- Select all records from the connection_details table:
SELECT * FROM connection_details;

-- Select all records from the mqtt_data table:
SELECT * FROM mqtt_data;

-- Select specific columns from the connection_details table:
SELECT id, host, port, username FROM connection_details;

-- Select records from the mqtt_data table based on a condition (for example, where connection_id is 1):
SELECT * FROM mqtt_data WHERE connection_id = 1;

-- Join the mqtt_data and connection_details tables to retrieve data along with connection details:
SELECT md.id AS mqtt_data_id, md.topic, md.message, md.host_name,
       cd.id AS connection_details_id, cd.host, cd.port
FROM mqtt_data md
         INNER JOIN connection_details cd ON md.connection_id = cd.id;
-- Select all unique topics:
SELECT DISTINCT topic FROM mqtt_data;

--Select all messages for a specific topic (e.g., "my_topic"):
SELECT message FROM mqtt_data WHERE topic = 'my_topic';

--Select all unique host names:
SELECT DISTINCT host_name FROM mqtt_data;

--Select all unique ports from the connection_details table:
SELECT DISTINCT port FROM connection_details;

--Select all unique connection IDs and their corresponding host names from the mqtt_data table:
SELECT DISTINCT md.connection_id, cd.host
FROM mqtt_data md
         JOIN connection_details cd ON md.connection_id = cd.id;
