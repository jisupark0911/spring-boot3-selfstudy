--SQL한줄 주석은 하이픈2개
INSERT INTO article (title, content) VALUES ('가가가가', '1111');
INSERT INTO article (title, content) VALUES ('나나나나', '2222');
INSERT INTO article (title, content) VALUES ('다다다다', '3333');

INSERT INTO article(title, content) VALUES('당신의 인생 영화는?','댓글 고');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?','댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?','댓글 고고고');

INSERT INTO comment(article_id, nickname, body) VALUES(4,'PARK','굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4,'KIM','아이 엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4,'CHOI','쇼생크 탈출');

INSERT INTO comment(article_id, nickname, body) VALUES(5,'PARK','치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5,'KIM','샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5,'CHOI','초밥');

INSERT INTO comment(article_id, nickname, body) VALUES(6,'PARK','조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6,'KIM','유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES(6,'CHOI','독서');