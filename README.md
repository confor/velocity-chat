# chat
A global chat plugin for the Velocity Minecraft proxy

I was disappointed with the current options so I made my own plugin. I hope its useful for others.

## Features
- **Global chat**: your player's voice is echoed across your whole velocity network
- **Log to console**: the chat can be sent to your console (configurable, can be disabled)
- **Configurable format**: chat format can be changed or formatted using MiniMessage tags (or translated!)
- **Global join/quit messages**: let your whole network know when a player joins or quits the velocity proxy (goes well with [HidePlayerJoinQuit](https://github.com/OskarZyg/HidePlayerJoinQuit/releases) or [SilentJoin](https://www.spigotmc.org/resources/silentjoin.34967/))

## Installation
Download the jar from GitHub releases (or compile your own) and drop it into your velocity's plugins folder. Restart the server to load a new plugin.

## Configuration
See `chat/config.toml` in your server's plugins folder. You need to restart for settings to take effect.

Messages support MiniMessage tags, read [this documentation](https://docs.adventure.kyori.net/minimessage#the-components) for more info.

## Name
I'm looking for a new plugin name, I don't really like "chat".

## Future fancy features
I would like to implement these features at some point:
- Welcome messages (MOTD?)
- Server switch messages
- Some command to clear chat
- Chat disabling (restricting the whole chat)
- Filter words from chat
- More templating placeholders (LP prefixes/suffixes, current server, date, etc)
