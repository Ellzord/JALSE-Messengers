## Messengers

Messengers is a [JALSE](https://github.com/Ellzord/JALSE) example project. It is a simulation of several people text messenging each other (upon receiving a message a reply is sent back). Below there are details about each of the classes and their function within the example (see the [Wiki](https://github.com/Ellzord/JALSE/wiki) for more information).

### Agents

| Class | Function |
| ------------- | ------|
| [Messenger](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/entities/Messenger.java) | Used to identify the people. |
| [Grass](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/entities/Message.java) | Used to identify the message. |

### Attributes

| Class | Function |
| ------------- | ------|
| [Text](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/attributes/Text.java) | The message content. |
| [From](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/attributes/From.java) | The senders identification. |

### Listeners

| Class | Function |
| ------------- | ------|
| [ReplyToMessage](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/listeners/ReplyToMessage.java) | Schedules a reply to be sent. |

### Actions

| Class | Function |
| ------------- | ------|
| [SendMessage](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/actions/SendMessage.java) | Sends a message to another person. |
