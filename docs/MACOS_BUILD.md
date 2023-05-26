# 使用 macOS 版本

原本的 binary 只有 windows 版，如果使用 macOS 可以參考下列步驟把漢化器跑起來。

請注意，下列步驟皆需要在終端機（terminal）內操作。

## 準備環境

以下教學採用 `homebrew` 來設置環境，請參考 [Homebrew 網站進行 brew 指令的安裝](https://brew.sh/index_zh-tw)

### 安裝 JDK

建置此專案需要 jdk 11 ，推薦使用 [Homebrew](https://brew.sh/index_zh-tw) 進行安裝。

```shell
brew install openjdk@11
```

### 下載 Gradle

編譯這個專案需要使用 `gradle` 工具的 6.x.x 版本，可以[透過此連結下載](https://gradle.org/next-steps/?version=6.8.3&format=bin)。

請記得下載的路徑，並將解壓縮後資料夾裡的 `bin` 路徑加入環境變數 `PATH` 中。（詳細操作在下方的「設定-設定環境變數-設定 gradle 路徑」段落）

### 下載其他函式庫 (csv parser)

#### 手動下載（目前此專案的版本）

本專案的當前設定檔案 `build.gradle` 中不會幫使用者自動下載 `com.univocity.parsers.csv` 函式庫。

請[點擊此處下載](https://oss.sonatype.org/service/local/repositories/releases/content/com/univocity/univocity-parsers/2.9.1/univocity-parsers-2.9.1.jar)。

並將下載的檔案放置到 `<本專案目錄>/libs` 中。（若在專案目錄下找不到 `libs` 資料夾，請自行建立一個）

#### 自動下載（選用）

**如不想手動下載此函式庫**，打開 `<本專案目錄>/build.gradle` 並編輯下列區塊：

```gradle
dependencies {
	testCompile(
		'junit:junit:4.12'
	)
	compile("com.jcraft:jzlib:1.1.3")
	compile("org.nlpcn:nlp-lang:1.7.6")
	compile("com.google.code.gson:gson:2.8.5")
	compile("commons-codec:commons-codec:1.11")
	compile("org.apache.httpcomponents:httpcore:4.4.6")
	compile("org.apache.httpcomponents:httpclient:4.5.3")
	compile("org.apache.httpcomponents:httpclient-cache:4.5.3")
	compile("org.apache.httpcomponents:httpmime:4.5.3")
	compile("org.apache.httpcomponents:fluent-hc:4.5.3")
	compile(files("libs/univocity-parsers-2.9.1.jar"))  // 刪除這行
	compile group: 'com.univocity', name: 'univocity-parsers', version: '2.9.1' // 修改成這行可以不用另外下載 jar
}
```

## 執行

### 設定環境變數

> 每次 run 之前先跑一次下列指令 [^1]

#### 設定 openjdk 的路徑

```shell
export PATH="$(brew --prefix)/opt/openjdk@11/bin:$PATH"
```

#### 設定 gradle 的路徑

假設你把放置 gradle 資料夾的位置為 `/Users/使用者帳號/Downloads/gradle-6.8.3`

```shell
export PATH="/Users/使用者帳號/Downloads/gradle-6.8.3/bin:$PATH"
```

### 執行程式

在 Terminal 中，切換到本專案資料夾內，再執行

```shell
gradle run
```

## 註解

[^1] 可以將上面 `export` 相關指令放入 `.bashrc`(若使用 `bash`) 或 `.zshrc`(若使用 `zsh`) 就可以不用每次都執行 (加入後需要重新啟動 Termial 才會生效)

## One more thing

使用 XIV on Mac 的朋友們，FFXIV 的根目錄可以在左上的 File-> Open Install Folder 找到，
第一次選擇會跳出錯誤視窗，提示你正確的 folder 名稱，不要被嚇到，再選一次，按下漢化就好。

路徑的結尾為 ffxiv ，比如說 `/Users/使用者帳號/Library/Application Support/XIV on Mac/ffxiv`
