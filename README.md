## 掲示板アプリ

### 概要

- スレッドを作成し、メッセージのやり取りが出来る.

### 画面

#### 掲示板一覧画面

URL：http://localhost:8080/threads

![2022-05-21 17_13_29](https://user-images.githubusercontent.com/76608722/169642710-de60f8f5-64d2-4a86-9842-b275864376f7.png)

- スレッドの一覧を見ることが出来る。
- スレッド作成画面に遷移することが出来る。

#### スレッド作成画面

URL：http://localhost:8080/makeThread

![2022-05-21 17_13_48](https://user-images.githubusercontent.com/76608722/169642715-0576e392-97d9-4471-8c00-501a1a181408.png)

- スレッドの新規作成が出来る。

#### メッセージ一覧画面

URL：http://localhost:8080/messages?threadNumber=2
![2022-05-21 17_21_04](https://user-images.githubusercontent.com/76608722/169642911-4096a80b-2202-4326-a2fb-6d46006176d4.png)

- 対象スレッドのメッセージを見ることが出来る。
- メッセージ投稿が出来る。

### 構成

- spring boot：Ver2.6.2
  - maven
  - h2
- html
- css
  - boot strap：Ver4.6.1
- font awesome：Ver5.10.0
- javascript

### 使用方法

1. Eclipse のワークスペースタブで右クリックして「インポート」を選択
2. インポートウィンドウで Maven/既存 Maven プロジェクトを選択
3. インポートしたプロジェクトを右クリックして、実行／Spring Boot アプリケーション
