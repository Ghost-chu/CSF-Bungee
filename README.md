
# CSF-Bungee
A bungeecord utility plugin that allows you to hide desired messages or errors that appear on server console and logs. The messages or errors are only hidden and not fixed! Useful if you want to hide errors or messages that spam the server console and get huge sized server logs. This plugin was made to stop console spam when you can't have the issue fixed, to prevent other issues because of intense console spam and unreadable console logs. It's very useful on modded servers where some mods can cause a lot of console spam. This does not hide all errors! Only the ones you configure to be hidden!

[SpigotMC](https://www.spigotmc.org/resources/95657) [Modrinth](https://modrinth.com/plugin/csf-bungee)

## Features
* Features
* Hides configured messages from appearing on server console and logs.
* Easy to configure. Check the plugin config file.
* Reload command to apply the config changes without a server restart.
* **This plugin cannot fix the errors like magic, just hide them.**

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
