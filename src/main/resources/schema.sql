DROP TABLE IF EXISTS FILM;
CREATE TABLE FILM
(
    FILM_ID     INT AUTO_INCREMENT PRIMARY KEY,
    TITLE       VARCHAR(50)  NOT NULL,
    STUDIOS     VARCHAR(50)  NOT NULL,
    PRODUCERS   VARCHAR(100) NOT NULL,
    LAUNCH_YEAR INT          NOT NULL,
    WINNER      VARCHAR(3)
);