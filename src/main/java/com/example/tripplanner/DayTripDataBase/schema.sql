CREATE TABLE OPERATOR (
    operatorId VARCHAR(128) NOT NULL UNIQUE,
    name VARCHAR(32) NOT NULL,
    phoneNo VARCHAR(32),
    location VARCHAR(32),
    localCode INT,
    PRIMARY KEY(operatorId)
);

CREATE TABLE DAYTRIP (
    dayTripId VARCHAR(128) NOT NULL UNIQUE,
    name VARCHAR(32) NOT NULL, 
    price FLOAT(32), 
    description VARCHAR(255), 
    location VARCHAR(32),
    localCode INT,
    date DATE NOT NULL, 
    timeStart TIMESTAMP, 
    timeEnd TIMESTAMP, 
    ageLimit INT, 
    difficulty VARCHAR(10), 
    capacity INT, 
    oId VARCHAR(32), 
    PRIMARY KEY(dayTripId), 
    FOREIGN KEY(oId) REFERENCES OPERATOR(operatorId)
);

CREATE TABLE BOOKING (
    bookingId VARCHAR(128) NOT NULL UNIQUE, 
    clientSSN VARCHAR(10), 
    clientEmail VARCHAR(32), 
    clientPhoneNumber VARCHAR(32), 
    clientCount INT, 
    date DATE NOT NULL, 
    isPaid BOOLEAN, 
    dtId VARCHAR(128), 
    PRIMARY KEY(bookingId), 
    FOREIGN KEY(dtId) REFERENCES DAYTRIP(dayTripId)
);

CREATE TABLE REVIEW (
    rating INT NOT NULL CHECK(rating BETWEEN 1 AND 5), 
    review VARCHAR(255), 
    date DATE NOT NULL, 
    clientSSN VARCHAR(10), 
    dtId VARCHAR(128),
    PRIMARY KEY(dtId, clientSSN),
	FOREIGN KEY(dtId) REFERENCES DAYTRIP(dayTripId)
);

INSERT INTO OPERATOR VALUES('7b6a30f4-d729-4398-9339-8c20a0d29157', 'Ævintýraferðir', '555-7895', 'Isafjörður', 1);
INSERT INTO OPERATOR VALUES('47d5af70-4ead-4fbf-8122-577fd12833ba', 'Hvalaskoðunin Lax', '565-6363', 'Isafjörður', 1);
INSERT INTO OPERATOR VALUES('4faa5e47-030b-458e-94d1-f90b61d32e9e', 'Bjarnaferðir', '555-1234', 'Bolungarvík', 1);
INSERT INTO OPERATOR VALUES('41d9c52e-6686-42b2-ad83-9f792b54524e', 'Icelandic Smoketrips', '589-8989', 'Reykjarfjörður', 1);
INSERT INTO OPERATOR VALUES('ef4802f3-7065-4668-92ae-9ca75a1457e9', 'Country Tours', '500-1234', 'Stykkishólmur', 2);
INSERT INTO OPERATOR VALUES('cf0692cb-243c-4c8b-8f26-fc6a2c807ea8', 'Ólafsferðir', '545-8521', 'Ólafsvík', 2);
INSERT INTO OPERATOR VALUES('2f9504c5-1d40-40f7-9ebb-d45060e75972', 'Jöklagarpar ehf.', '565-3434', 'Arnarstapi', 2);
INSERT INTO OPERATOR VALUES('c8b29e98-4810-4f58-ab0d-c83fb2b7948a', 'Iceland Travels', '521-2630', 'Reykjavík', 3);
INSERT INTO OPERATOR VALUES('0da1ae1f-b454-4537-8301-4b65a5d79575', 'All around tours', '555-2345', 'Reykjavík', 3);
INSERT INTO OPERATOR VALUES('deadc463-6742-4ed1-b33b-2dac200dd3d8', 'Reykjavik travelling', '577-2775', 'Reykjavík', 3);
INSERT INTO OPERATOR VALUES('8ba8816b-3cb3-498c-a929-cfa688d4c925', 'Reykjavik experience', '555-2225', 'Reykjavík', 3);
INSERT INTO OPERATOR VALUES('b6b684ba-630f-4204-b516-1ea9d21306ae', 'Army tours', '555-2345', 'Keflavík', 3);
INSERT INTO OPERATOR VALUES('449ca3fc-ae5f-41bb-a2f5-d0b6a8210cd7', 'Northern lights wow', '588-2888', 'Selfoss', 3);
INSERT INTO OPERATOR VALUES('6c9374dc-5ebd-493c-a539-f2ee7cbaf40e', 'Vestmanna-Boat tours', '589-6148', 'Vestmannaeyjar', 4);
INSERT INTO OPERATOR VALUES('70c2ed07-8d26-4a94-a198-7bc93cdf28cb', 'Swing along', '569-6969', 'Vestmannaeyjar', 4);
INSERT INTO OPERATOR VALUES('c61cfb0d-9818-47c9-856f-4ecbddae964a', 'Ferðir Egils', '578-9876', 'Egilsstaðir', 5);
INSERT INTO OPERATOR VALUES('31437b35-f8c4-4cc0-9aec-0f1e5d81f4f0', 'Subbuferðir', '545-5454', 'Seyðisfjörður', 5);
INSERT INTO OPERATOR VALUES('2a93cc1f-0b98-4110-95d2-b815667c8431', 'Nestrips', '577-6471', 'Neskaupsstaður', 5);
INSERT INTO OPERATOR VALUES('7b4c985c-05ae-47ca-a156-75f872a62d32', 'Arctic Adventures', '555-0000', 'Akureyri', 6);
INSERT INTO OPERATOR VALUES('05d97901-4b34-43f2-9967-ba5b126d7d39', 'Iceland Excursions', '567-8910', 'Akureyri', 6);
INSERT INTO OPERATOR VALUES('ec44e5a7-805a-45eb-8deb-6268dcebdeb3', 'House tours', '566-6610', 'Húsavík', 6);
INSERT INTO OPERATOR VALUES('2f35b71f-ee44-431e-b51a-b4319f3ebc90', 'Smokehill tours', '551-9050', 'Reykjahlíð', 6);