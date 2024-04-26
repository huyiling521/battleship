package network;

/**
 * The MessageConstant class holds a set of constants that define various types of message identifiers.
 * These identifiers are used to differentiate between the types of messages that can be sent and received
 * in a networked Battleship game, facilitating the communication protocol between the client and the server.
 *
 * Each constant is a String that represents a specific command or type of message in the game's protocol.
 * They are used as prefixes in the game's communication to quickly identify the message's purpose.
 */
public class MessageConstant {

    /** Prefix for response messages from the server. */
    public static final String RESPONSE = "RESPONSE:";

    /** Prefix for request messages to the server. */
    public static final String REQUEST = "REQUEST:";

    /** Prefix for notification messages from the server. */
    public static final String NOTIFICATION = "NOTE:";

    /** Prefix for messages indicating an opponent's attack move. */
    public static final String OPPONENT_ATTACK = "OPPONENT_ATTACK:";

    /** Prefix for messages sent by the opponent. */
    public static final String OPPONENT_MESSAGE = "OPPONENT_MESSAGE:";

    /** Prefix for messages containing the opponent's name. */
    public static final String OPPONENT_NAME = "OPPONENT_NAME:";

    /** Prefix for messages signaling the start of the game. */
    public static final String GAME_START = "GAME_START:";

    /** Prefix for messages signaling the end of the game. */
    public static final String GAME_END = "GAME_END:";

    /** Prefix for messages signaling that a player has quit the game. */
    public static final String GAME_QUIT = "GAME_QUIT:";

    /** Prefix for messages declaring the game is over. */
    public static final String GAME_OVER = "GAME_OVER:";

    /** Prefix for messages during the connection phase. */
    public static final String CONNECTION = "CONNECTION:";

    /** Prefix for system messages. */
    public static final String SYSTEM_MESSAGE = "SYSTEM_MESSAGE:";

    /** Prefix for successful operation messages. */
    public static final String SUCCESS = "SUCCESS:";

    /** Prefix for error messages. */
    public static final String ERROR = "ERROR:";

    // Player-related constants

    /** Prefix for messages related to player's setting. */
    public static final String SETTING = "MY_SETTING:";

    /** Prefix for messages related to player's attack. */
    public static final String ATTACK = "MY_ATTACK:";

    /** Prefix for player's chat messages. */
    public static final String MESSAGE = "MY_MESSAGE:";

    /** Prefix for messages related to setting player's name. */
    public static final String NAME_SETTING = "MY_NAME:";

    /** Message indicating that it's not the player's turn. */
    public static final String INVALID_TURN = "Sorry, it's not your turn.";
}
