# CSF-Bungee
ConsoleSpamFix for BungeeCord.

## Features
- Filter for both BungeeCord and Plugin logger.
- Regex & Contains two modes support

## Configuration

```yaml
#Add here the messages you want to hide from your console and logs.
#Each line represents a new message.
#NOTE! You don't need to add the full message!
#The plugin will hide all messages that contain the text added below.
# EXAMPLE:
#
# Messages-To-Hide-Filter:
# - 'WARNING: unlocalizedName'
#
# To use regex, add "@" prefix in that line
#
# All messages that will appear in the server console that contain these words 'WARNING: unlocalizedName' will be hidden and they will not appear on logs or console.
# If you have large errors you don'tt have to type the full error. But the more words you add the more accurate will be and will not hide other messages that may contain the words added in the filter.

Messages-To-Hide-Filter:
  - 'WARNING: unlocalizedName'
  - 'look up profile properties for'
  - 'InitialHandler - NativeIoException: readAddress(..) failed: Connection reset by peer'
  - 'Unexpected packet received during login process'
```

## 关于重传

该插件是我在 Bilicraft-Community 任职时编写的插件，起初是为了解决服务器BungeeCord被CC攻击后大面积错误信息的问题。  
在离开 Bilicraft 后我决定继续对其维护更新。

## 重传是否存在版权问题

不存在，本插件 100% 由我个人编写，没有任何版权问题。  
同时，在认知期间 Bilicraft 并未向我支付任何劳动报酬，因此也不存在任何的风险。
