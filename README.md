## Messengers
Messengers is a [JALSE](https://github.com/Ellzord/JALSE) example project. It is a simulation of several people text messenging each other (upon receiving a message a reply is sent back).

### Simulation life-cycle
1. [Messenger](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/entities/Messenger.java)s are created.
2. Messengers are then paired together.
3. A [SendMessage](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/actions/SendMessage.java) is scheduled for each messenger.
4. A message is sent by creating a [Message](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/entities/Message.java) entity.
5. Sending a messages causes [ReplyToMessage](https://github.com/Ellzord/JALSE-Messengers/blob/master/Messengers/src/messengers/listeners/ReplyToMessage.java) to fire sending a reply back.
6. Repeat from 4.

### Model key
![Model key](/model-key.png)

### Model
![Model](/messengers-model.png)

### More
See the [Wiki](https://github.com/Ellzord/JALSE/wiki) for more information.
