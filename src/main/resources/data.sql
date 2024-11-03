/**スレッド名*/
insert into thread (thread_name,created_ymdhms,updated_ymdhms)
values ('雑談','20220101101010','20220101101010');
insert into thread (thread_name,created_ymdhms,updated_ymdhms)
values ('今年のベストゲームソフト','20220101101010','20220101101010');
insert into thread (thread_name,created_ymdhms,updated_ymdhms)
values ('おすすめの映画','20220101101010','20220101101010');

/**メッセージ*/
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms)
values (1,'今何をしていますか？','20220101101010','匿名','20220101101010');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms)
values (1,'本を読んでいます','20220101101510','名無しさん','20220101101510');

insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms)
values (2,'今年一番面白かったゲームソフトは何？','20220101101010','匿名','20220101101010');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms)
values (2,'アンチャーテッド！','20220101101510','ネイサン','20220101101510');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms)
values (2,'スマブラ','20220101102510','匿名','20220101102510');

insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms)
values (3,'おすすめの映画を教えてください','20220101101010','匿名','20220101101010');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms)
values (3,'バットマン','20220101102010','名無しさん','20220101102010');