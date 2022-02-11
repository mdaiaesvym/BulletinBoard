/**スレッド名*/
insert into threads (thread_name,created_at,updated_at)
values ('雑談','2022-01-01 10:10:10','2022-01-01 10:10:10');
insert into threads (thread_name,created_at,updated_at)
values ('今年のベストゲームソフト','2022-01-01 10:10:10','2022-01-01 10:10:10');
insert into threads (thread_name,created_at,updated_at)
values ('おすすめの映画','2022-01-01 10:10:10','2022-01-01 10:10:10');

/**メッセージ*/
insert into messages (thread_number,message,created_at,created_name,updated_at)
values (1,'今何をしていますか？','2022-01-01 10:10:10','匿名','2022-01-01 10:10:10');
insert into messages (thread_number,message,created_at,created_name,updated_at)
values (1,'本を読んでいます','2022-01-01 10:15:10','名無しさん','2022-01-01 10:15:10');

insert into messages (thread_number,message,created_at,created_name,updated_at)
values (2,'今年一番面白かったゲームソフトは何？','2022-01-01 10:10:10','匿名','2022-01-01 10:10:10');
insert into messages (thread_number,message,created_at,created_name,updated_at)
values (2,'アンチャーテッド！','2022-01-01 10:15:10','ネイサン','2022-01-01 10:15:10');
insert into messages (thread_number,message,created_at,created_name,updated_at)
values (2,'スマブラ','2022-01-01 10:20:10','匿名','2022-01-01 10:20:10');

insert into messages (thread_number,message,created_at,created_name,updated_at)
values (3,'おすすめの映画を教えてください','2022-01-01 10:10:10','匿名','2022-01-01 10:10:10');
insert into messages (thread_number,message,created_at,created_name,updated_at)
values (3,'バットマン','2022-01-01 10:15:10','名無しさん','2022-01-01 10:15:10');