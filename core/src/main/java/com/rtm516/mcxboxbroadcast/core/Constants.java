package com.rtm516.mcxboxbroadcast.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v800.Bedrock_v800;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.Properties;

public class Constants {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantConverter())
            .registerTypeAdapter(Date.class, new DateConverter())
            .disableHtmlEscaping()
            .create();

    public static final Gson GSON_NULLS = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantConverter())
            .registerTypeAdapter(Date.class, new DateConverter())
            .serializeNulls()
            .create();

    // Werte aus Konfig
    public static final String SERVICE_CONFIG_ID;
    public static final String TEMPLATE_NAME;
    public static final String TITLE_ID;

    // Endpunkte
    public static final String CREATE_SESSION;
    public static final String JOIN_SESSION;

    public static final String PLAYFAB_LOGIN;
    public static final URI START_SESSION;
    public static final String RTC_WEBSOCKET_FORMAT;

    public static final URI RTA_WEBSOCKET;
    public static final URI CREATE_HANDLE;

    public static final String PEOPLE;
    public static final String USER_PRESENCE;
    public static final URI FOLLOWERS;
    public static final URI SOCIAL;
    public static final URI SOCIAL_SUMMARY;
    public static final String FOLLOWER;
    public static final String PROFILE_SETTINGS;

    public static final String GALLERY;

    public static final int ConnectionTypeWebRTC = 3;
    public static final int MAX_FRIENDS = 2000;
    public static final BedrockCodec BEDROCK_CODEC = Bedrock_v800.CODEC;

    static {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.err.println("⚠️ Konnte config.properties nicht laden – Standardwerte werden genutzt.");
        }

        SERVICE_CONFIG_ID = props.getProperty("service_config_id", "4fc10100-5f7a-4470-899b-280835760c07");
        TEMPLATE_NAME     = props.getProperty("template_name", "MinecraftLobby");
        TITLE_ID          = props.getProperty("title_id", "896928775");

        CREATE_SESSION = "https://sessiondirectory.xboxlive.com/serviceconfigs/" + SERVICE_CONFIG_ID + "/sessionTemplates/" + TEMPLATE_NAME + "/sessions/%s";
        JOIN_SESSION   = "https://sessiondirectory.xboxlive.com/handles/%s/session";

        PLAYFAB_LOGIN         = props.getProperty("playfab_login", "https://20ca2.playfabapi.com/Client/LoginWithXbox");
        START_SESSION         = URI.create(props.getProperty("start_session", "https://authorization.franchise.minecraft-services.net/api/v1.0/session/start"));
        RTC_WEBSOCKET_FORMAT  = props.getProperty("rtc_ws", "wss://signal.franchise.minecraft-services.net/ws/v1.0/signaling/%s");

        RTA_WEBSOCKET = URI.create("wss://rta.xboxlive.com/connect");
        CREATE_HANDLE = URI.create("https://sessiondirectory.xboxlive.com/handles");

        PEOPLE           = "https://social.xboxlive.com/users/me/people/xuid(%s)";
        USER_PRESENCE    = "https://userpresence.xboxlive.com/users/xuid(%s)/devices/current/titles/current";
        FOLLOWERS        = URI.create("https://peoplehub.xboxlive.com/users/me/people/followers");
        SOCIAL           = URI.create("https://peoplehub.xboxlive.com/users/me/people/social");
        SOCIAL_SUMMARY   = URI.create("https://social.xboxlive.com/users/me/summary");
        FOLLOWER         = "https://social.xboxlive.com/users/me/people/follower/xuid(%s)";
        PROFILE_SETTINGS = "https://profile.xboxlive.com/users/xuid(%s)/profile/settings?settings=Gamertag";

        GALLERY = "https://persona.franchise.minecraft-services.net/api/v1.0/gallery";
    }
}