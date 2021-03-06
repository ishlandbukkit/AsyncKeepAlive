# The future of AsyncKeepAlive
In 1.16, Paper has made a lot of changes to networking that caused this plugin is NOT working properly. And there is NO way to make it work properly again. Fixing these things isn't easy or fun and takes a lot of time and energy that I don't have. 
I have no plans to continue updating AsyncKeepAlive and no alternatives is present. 
AsyncKeepAlive will still works just fine from 1.7 to 1.11. I have no plans to continue fixing inconsistencies on 1.12+. 

# AsyncKeepAlive
[![Build Status](https://ci.ishland.com/job/AsyncKeepAlive/badge/icon)](https://ci.ishland.com/job/AsyncKeepAlive/)

This is a plugin that reduce the probability of disconnects

Warning: This plugin is currently not stable. I didn't update it due to lack to time.

# Compatibility
- API Version: 1.14-R0.1-SNAPSHOT
- Dependencies: ProtocolLib
- Requirements: Java 7 or above

# How it works
- Open an asynchronous thread timing (every 4 seconds) to send a KeepAlive (heartbeat packet) to the online players, reducing the probability of players disconnect due to low TPS, instant stuck and network packet loss. This plugin can only reduce the probability of disconnects and cannot increase the network transmission speed.
- Open an asynchronous thread receive all KeepAlive (heartbeat packet) and filter the KeepAlives. Cancel the packet whick is sent by the plugin and let the others go. This is required on 1.12+, and a bug fix on older versions (The latency is displayed as invaild).

# Some Information
- The server itself will send a KeepAlive to all clients every 20 seconds. The client must reply to the heartbeat packet within 30 seconds. Otherwise, the server is offline and the connection is displayed as dropped. If the server suddenly gets stuck, it will inevitably cause some players to disconnect. If It stucks more than 20 seconds, all the players will be disconnected. Because the heartbeat packet is sent in the main thread in a synchronous manner, the low TPS will cause the packet to be sent. Speed and response speed are slowing down. This plugin opens up a new asynchronous thread (not affected by TPS) and sends a heartbeat packet every 4 seconds, thus reducing the probability of players falling offline due to poor network conditions and low server TPS and instant card conditions.

## This plugin uses bStats.
### bStats sends the following data:
- Your server's randomly generated UUID
- The amount of players on your server
- The online mode of your server
- The bukkit version of your server (It seems like it doesn't send this)
- The java version of your system (e.g. Java 8)
- The name of your OS (e.g. Windows)
- The version of your OS
- The architecture of your OS (e.g. amd64)
- The system cores of your OS (e.g. 8)
- bStats-supported plugins
- Plugin version of bStats-supported plugins

### bStats page: https://bstats.org/plugin/bukkit/AsyncKeepAlive

# Tested versions
- Spigot 1.14
- Paperspigot 1.13.2 with ProtocolLib 4.4.0
- Paperspigot 1.12.2 with ProtocolLib 4.4.0
- Paperspigot 1.11.2 with ProtocolLib 4.4.0
- Bukkit 1.7.10 with ProtocolLib unknown by Chinese MCBBS 夜夜夜、

# Known issues
Nope

# Development builds:
https://ci.ishland.com/job/AsyncKeepAlive/ (Take the first jar)

## Sorry for my poor English, I am Chinese.
