package com.wendy.jnbus.vo.fir;

/**
 * 类描述：获取版本号-fir.im
 * 创建人：XieWQ
 * 创建时间：2017/5/15 0015 下午 16:46
 */
public class AppVersion {

    private String name;//应用名称
    private String version;//版本
    private String changelog;//更新日志
    private String versionShort;//	版本编号(兼容旧版字段)
    private String build;//编译号
    private String install_url;//安装地址(新增字段)

    @Override
    public String toString() {
        return "AppVersion{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", changelog='" + changelog + '\'' +
                ", versionShort='" + versionShort + '\'' +
                ", build='" + build + '\'' +
                ", install_url='" + install_url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChangelog() {
        return changelog;
    }

    public void setChangelog(String changelog) {
        this.changelog = changelog;
    }

    public String getVersionShort() {
        return versionShort;
    }

    public void setVersionShort(String versionShort) {
        this.versionShort = versionShort;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getInstall_url() {
        return install_url;
    }

    public void setInstall_url(String install_url) {
        this.install_url = install_url;
    }
}
