Here's the rephrased markdown without changing the text between backticks:

## Description / 説明

This is a lightweight server application designed to function as a backend for the demo app.

デモアプリのバックエンドとして機能する軽量なサーバーアプリです。

### Setup / 設定

<details>
  <summary>English / 英語</summary>

* Ensure you have an existing Firebase project in your Firebase console, or create a new one.
* Enable Cloud Functions.
* Install Firebase CLI tools using the command `npm install -g firebase-tools`.
* Log in to Firebase CLI with `firebase login`.
* After successful login, initialize Firebase in the server directory using `firebase init`.
* Choose ` ◉ Functions: Configure a Cloud Functions directory and its files` and press enter.
* Select `Use an existing project`.
* Choose the project created in step 1.
* Choose `JavaScript`.
* Respond **No** to ` Do you want to use ESLint to catch probable bugs and enforce style?`.
* Respond **No** to `File functions/index.js already exists. Overwrite?` (ensure this is correct).
* Respond **Yes** to `Do you want to install dependencies with npm now?`.
* Configure the `.env` file according to the `.env.sample`.
* Run `firebase deploy` to build and deploy the server to Firebase Cloud Functions.
* Wait for the process to complete, and your server should be up and running.

</details>


<details>
  <summary>Japanese / 日本語</summary>

* FirebaseコンソールにFirebaseプロジェクトを作成するか、既存のプロジェクトを確認してください。
* Cloud Functionsを有効にします。
* Firebase CLIツールをインストールします: `npm install -g firebase-tools`.
* `firebase login` を使ってFirebase CLIにログインします。
* ログインが成功したら、サーバーディレクトリで `firebase init` を実行してFirebaseを初期化します。
* ` ◉ Functions: Configure a Cloud Functions directory and its files` を選択してEnterを押します。
* `既存のプロジェクトを使用`を選択します。
* ステップ1で作成したプロジェクトを選択します。
* `JavaScript` を選択します。
* ` Do you want to use ESLint to catch probable bugs and enforce style?` に対して**いいえ**と回答します。
* `File functions/index.js already exists. Overwrite?` に対して**いいえ**と回答します（必ず確認してください）。
* `Do you want to install dependencies with npm now?` に対して**はい**と回答します。
* `.env.sample`に基づいて `.env` ファイルを設定します。
* `firebase deploy` を実行して、サーバーをFirebase Cloud Functionsにデプロイします。
* デプロイが完了するまで待ち、サーバーが稼働していることを確認します。

</details>

### Support / サポート
If you encounter any issues, please open an issue.

何か問題が発生した場合は、問題を報告してください。