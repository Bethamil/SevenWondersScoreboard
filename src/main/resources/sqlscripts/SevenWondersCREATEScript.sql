CREATE SCHEMA SevenWondersScoreboard ;

CREATE TABLE SevenWondersScoreboard.players(
`name` varchar(32) NOT NULL PRIMARY KEY);

CREATE TABLE SevenWondersScoreboard.gameresults(
 `gameresults_id` int AUTO_INCREMENT PRIMARY KEY,
 `date` DATETIME NOT NULL,
`player_1` varchar(32) NOT NULL,
`player_2` varchar(32) NOT NULL,
`winner` varchar(32) NOT NULL,
`won_by` varchar(16) NOT NULL,
`score_p1` int,
`score_p2` int,
CONSTRAINT `fk_gameresults_player1`
    FOREIGN KEY (`player_1`)
    REFERENCES `SevenWondersScoreboard`.`players` (`name`),
CONSTRAINT `fk_gameresults_player2`
    FOREIGN KEY (`player_2`)
    REFERENCES `SevenWondersScoreboard`.`players` (`name`),
CONSTRAINT `fk_gameresults_winner`
    FOREIGN KEY (`winner`)
    REFERENCES `SevenWondersScoreboard`.`players` (`name`)
);

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON SevenWondersScoreboard.* TO 'admin'@'localhost';

