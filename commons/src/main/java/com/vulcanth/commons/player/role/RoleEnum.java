package com.vulcanth.commons.player.role;

public enum RoleEnum {

    MASTER("master", "§6Master", "§6[Master] ", "role.master"),
    GERENTE("gerente", "§4Gerente", "§4[Gerente] ", "role.gerente"),
    ADMIN("admin", "§cAdmin", "§c[Admin] ", "role.admin"),
    MODERADOR("mod", "§2Moderador", "§2[Moderador] ", "role.mod"),
    AJUDANTE("ajudante", "§eAjudante", "§e[Ajudante] ", "role.ajudante"),
    CONSTRUTOR("construtor", "§aConstrutor", "§a[Construtor] ", "role.construtor"),
    STREAMER("streamer", "§9Streamer", "§9[Streamer] ", "role.streamer"),
    YOUTUBER("yt", "§cYouTuber", "§c[YouTuber] ", "role.yt"),
    MVPPLUS("mvpplus", "§bMVP§6+§b", "§b[MVP§6+§b] ", "role.mvpplus"),
    MVP("mvp", "§6MVP", "§6[MVP] ", "role.mvp"),
    VIP("vip", "§aVIP", "§a[VIP] ", "role.vip"),
    MEMBRO("membro", "§7Membro", "§7", null);

    private final String groupName;
    private final String name;
    private final String prefix;
    private final String permission;

    RoleEnum(String groupName, String name, String prefix, String permission) {
        this.groupName = groupName;
        this.name = name;
        this.prefix = prefix;
        this.permission = permission;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getPermission() {
        return this.permission;
    }
}
