/**スレッド名*/
insert into thread (thread_name,created_ymdhms,created_name,updated_ymdhms,updated_name)
values ('雑談','20220101101010','山田一郎','20220101101010','山田一郎');
insert into thread (thread_name,created_ymdhms,created_name,updated_ymdhms,updated_name)
values ('今年のベストゲームソフト','20220101101010','匿名','20220101101010','匿名');
insert into thread (thread_name,created_ymdhms,created_name,updated_ymdhms,updated_name)
values ('おすすめの映画','20220101101010','匿名','20220101101010','匿名');

/**メッセージ*/
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms,updated_name)
values (1,'今何をしていますか？','20220101101010','山田一郎','20220101101010','山田一郎');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms,updated_name)
values (1,'本を読んでいます','20220101101510','名無しさん','20220101101510','名無しさん');

insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms,updated_name)
values (2,'今年一番面白かったゲームソフトは何？','20220101101010','匿名','20220101101010','匿名');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms,updated_name)
values (2,'アンチャーテッド！','20220101101510','ネイサン','20220101101510','ネイサン');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms,updated_name)
values (2,'スマブラ','20220101102510','匿名','20220101102510','匿名');

insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms,updated_name)
values (3,'おすすめの映画を教えてください','20220101101010','匿名','20220101101010','匿名');
insert into message (thread_number,message,created_ymdhms,created_name,updated_ymdhms,updated_name)
values (3,'バットマン','20220101102010','名無しさん','20220101102010','名無しさん');