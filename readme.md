# Blockchain

Simple java blockchain to demonstrate and understand blockchains in general.



# Usage

Run `java blockchainRun`

There are 5 commands.

1. `[User] create`
    Creates a new user
2. `[User] mine`
    Mines a coin and gives it to the user
3. `[Giver] [Receiver] give`
    Takes value from giver and gives it to receiver, it will prompt for an amount once you run the command.
4. `save [filename]`
    Saves to the filename
5. `load [filename]`
    Loads from the file, deletes current blockchain

Start by creating a few users with different usernames. Next mine a bit and give to different users. The format that the blockchain is displayed in is:

```
[ (Blockchain start)
    { (Block, may or may not contain transactions)

        Sender: ...    (Transaction)
        Receiver: ...
        Amount...

    }
]
```
