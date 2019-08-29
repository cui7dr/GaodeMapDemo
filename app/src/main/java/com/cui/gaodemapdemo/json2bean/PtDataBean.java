package com.cui.gaodemapdemo.json2bean;

import java.util.Date;

/**
 * 站点数据信息实体类
 *
 * @author cui7dr by 2019/08/24
 */

public class PtDataBean {

    private PointData pointData;

    public PointData getPointData() {
        return pointData;
    }

    public void setPointData(PointData pointData) {
        this.pointData = pointData;
    }

    public class PointData {
        private DataInfo data_info;

        public DataInfo getData_info() {
            return data_info;
        }

        public void setData_info(DataInfo data_info) {
            this.data_info = data_info;
        }

        public class DataInfo {
            private double acc;
            private IsHgMap isHgMap;
            private double hcco2;
            private int pdjg;
            private double cojg;
            private Date pubTime;
            private SynaStatusMap synaStatusMap;
            private double dqy;
            private int nojg;
            private PdjgMap pdjgMap;
            private StatusMap statusMap;
            private int hpys;
            private double ddjd;
            private long id;
            private int noOverNums;
            private String rlzlStr;
            private String jzbh;
            private String pdjgStr;
            private double noco2;
            private String hpysStr;
            private int ptInfoId;
            private int rgB;
            private double cco2;
            private int noxz;
            private String noIsHgStr;
            private String tp1;
            private int cdpd;
            private String hphm;
            private String synaStatusStr;
            private int status;
            private int coxz;
            private String statusStr;
            private String testNo;
            private String jzjcbh;
            private NoIsHgMap noIsHgMap;
            private int hcjg;
            private YdzIsHgMap ydzIsHgMap;
            private String cdbh;
            private int ydzxz;
            private double fs;
            private double wd;
            private int speed;
            private double ydz;
            private double sd;
            private String fx;
            private double vsp;
            private int rgbXz;
            private int rlzl;
            private int noIsHg;
            private HpysMap hpysMap;
            private int vehDateId;
            private double ddwd;
            private Date updateTime;
            private RlzlMap rlzlMap;
            private int synaStatus;
            private int ptTeleLineId;
            private Date createTime;
            private PtInfo ptInfo;
            private int isHg;
            private String isHgStr;
            private double coco2;
            private Date testDate;

            public double getAcc() {
                return acc;
            }

            public void setAcc(double acc) {
                this.acc = acc;
            }

            public IsHgMap getIsHgMap() {
                return isHgMap;
            }

            public void setIsHgMap(IsHgMap isHgMap) {
                this.isHgMap = isHgMap;
            }

            public double getHcco2() {
                return hcco2;
            }

            public void setHcco2(double hcco2) {
                this.hcco2 = hcco2;
            }

            public int getPdjg() {
                return pdjg;
            }

            public void setPdjg(int pdjg) {
                this.pdjg = pdjg;
            }

            public double getCojg() {
                return cojg;
            }

            public void setCojg(double cojg) {
                this.cojg = cojg;
            }

            public Date getPubTime() {
                return pubTime;
            }

            public void setPubTime(Date pubTime) {
                this.pubTime = pubTime;
            }

            public SynaStatusMap getSynaStatusMap() {
                return synaStatusMap;
            }

            public void setSynaStatusMap(SynaStatusMap synaStatusMap) {
                this.synaStatusMap = synaStatusMap;
            }

            public double getDqy() {
                return dqy;
            }

            public void setDqy(double dqy) {
                this.dqy = dqy;
            }

            public int getNojg() {
                return nojg;
            }

            public void setNojg(int nojg) {
                this.nojg = nojg;
            }

            public PdjgMap getPdjgMap() {
                return pdjgMap;
            }

            public void setPdjgMap(PdjgMap pdjgMap) {
                this.pdjgMap = pdjgMap;
            }

            public StatusMap getStatusMap() {
                return statusMap;
            }

            public void setStatusMap(StatusMap statusMap) {
                this.statusMap = statusMap;
            }

            public int getHpys() {
                return hpys;
            }

            public void setHpys(int hpys) {
                this.hpys = hpys;
            }

            public double getDdjd() {
                return ddjd;
            }

            public void setDdjd(double ddjd) {
                this.ddjd = ddjd;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public int getNoOverNums() {
                return noOverNums;
            }

            public void setNoOverNums(int noOverNums) {
                this.noOverNums = noOverNums;
            }

            public String getRlzlStr() {
                return rlzlStr;
            }

            public void setRlzlStr(String rlzlStr) {
                this.rlzlStr = rlzlStr;
            }

            public String getJzbh() {
                return jzbh;
            }

            public void setJzbh(String jzbh) {
                this.jzbh = jzbh;
            }

            public String getPdjgStr() {
                return pdjgStr;
            }

            public void setPdjgStr(String pdjgStr) {
                this.pdjgStr = pdjgStr;
            }

            public double getNoco2() {
                return noco2;
            }

            public void setNoco2(double noco2) {
                this.noco2 = noco2;
            }

            public String getHpysStr() {
                return hpysStr;
            }

            public void setHpysStr(String hpysStr) {
                this.hpysStr = hpysStr;
            }

            public int getPtInfoId() {
                return ptInfoId;
            }

            public void setPtInfoId(int ptInfoId) {
                this.ptInfoId = ptInfoId;
            }

            public int getRgB() {
                return rgB;
            }

            public void setRgB(int rgB) {
                this.rgB = rgB;
            }

            public double getCco2() {
                return cco2;
            }

            public void setCco2(double cco2) {
                this.cco2 = cco2;
            }

            public int getNoxz() {
                return noxz;
            }

            public void setNoxz(int noxz) {
                this.noxz = noxz;
            }

            public String getNoIsHgStr() {
                return noIsHgStr;
            }

            public void setNoIsHgStr(String noIsHgStr) {
                this.noIsHgStr = noIsHgStr;
            }

            public String getTp1() {
                return tp1;
            }

            public void setTp1(String tp1) {
                this.tp1 = tp1;
            }

            public int getCdpd() {
                return cdpd;
            }

            public void setCdpd(int cdpd) {
                this.cdpd = cdpd;
            }

            public String getHphm() {
                return hphm;
            }

            public void setHphm(String hphm) {
                this.hphm = hphm;
            }

            public String getSynaStatusStr() {
                return synaStatusStr;
            }

            public void setSynaStatusStr(String synaStatusStr) {
                this.synaStatusStr = synaStatusStr;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCoxz() {
                return coxz;
            }

            public void setCoxz(int coxz) {
                this.coxz = coxz;
            }

            public String getStatusStr() {
                return statusStr;
            }

            public void setStatusStr(String statusStr) {
                this.statusStr = statusStr;
            }

            public String getTestNo() {
                return testNo;
            }

            public void setTestNo(String testNo) {
                this.testNo = testNo;
            }

            public String getJzjcbh() {
                return jzjcbh;
            }

            public void setJzjcbh(String jzjcbh) {
                this.jzjcbh = jzjcbh;
            }

            public NoIsHgMap getNoIsHgMap() {
                return noIsHgMap;
            }

            public void setNoIsHgMap(NoIsHgMap noIsHgMap) {
                this.noIsHgMap = noIsHgMap;
            }

            public int getHcjg() {
                return hcjg;
            }

            public void setHcjg(int hcjg) {
                this.hcjg = hcjg;
            }

            public YdzIsHgMap getYdzIsHgMap() {
                return ydzIsHgMap;
            }

            public void setYdzIsHgMap(YdzIsHgMap ydzIsHgMap) {
                this.ydzIsHgMap = ydzIsHgMap;
            }

            public String getCdbh() {
                return cdbh;
            }

            public void setCdbh(String cdbh) {
                this.cdbh = cdbh;
            }

            public int getYdzxz() {
                return ydzxz;
            }

            public void setYdzxz(int ydzxz) {
                this.ydzxz = ydzxz;
            }

            public double getFs() {
                return fs;
            }

            public void setFs(double fs) {
                this.fs = fs;
            }

            public double getWd() {
                return wd;
            }

            public void setWd(double wd) {
                this.wd = wd;
            }

            public int getSpeed() {
                return speed;
            }

            public void setSpeed(int speed) {
                this.speed = speed;
            }

            public double getYdz() {
                return ydz;
            }

            public void setYdz(double ydz) {
                this.ydz = ydz;
            }

            public double getSd() {
                return sd;
            }

            public void setSd(double sd) {
                this.sd = sd;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public double getVsp() {
                return vsp;
            }

            public void setVsp(double vsp) {
                this.vsp = vsp;
            }

            public int getRgbXz() {
                return rgbXz;
            }

            public void setRgbXz(int rgbXz) {
                this.rgbXz = rgbXz;
            }

            public int getRlzl() {
                return rlzl;
            }

            public void setRlzl(int rlzl) {
                this.rlzl = rlzl;
            }

            public int getNoIsHg() {
                return noIsHg;
            }

            public void setNoIsHg(int noIsHg) {
                this.noIsHg = noIsHg;
            }

            public HpysMap getHpysMap() {
                return hpysMap;
            }

            public void setHpysMap(HpysMap hpysMap) {
                this.hpysMap = hpysMap;
            }

            public int getVehDateId() {
                return vehDateId;
            }

            public void setVehDateId(int vehDateId) {
                this.vehDateId = vehDateId;
            }

            public double getDdwd() {
                return ddwd;
            }

            public void setDdwd(double ddwd) {
                this.ddwd = ddwd;
            }

            public Date getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Date updateTime) {
                this.updateTime = updateTime;
            }

            public RlzlMap getRlzlMap() {
                return rlzlMap;
            }

            public void setRlzlMap(RlzlMap rlzlMap) {
                this.rlzlMap = rlzlMap;
            }

            public int getSynaStatus() {
                return synaStatus;
            }

            public void setSynaStatus(int synaStatus) {
                this.synaStatus = synaStatus;
            }

            public int getPtTeleLineId() {
                return ptTeleLineId;
            }

            public void setPtTeleLineId(int ptTeleLineId) {
                this.ptTeleLineId = ptTeleLineId;
            }

            public Date getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Date createTime) {
                this.createTime = createTime;
            }

            public PtInfo getPtInfo() {
                return ptInfo;
            }

            public void setPtInfo(PtInfo ptInfo) {
                this.ptInfo = ptInfo;
            }

            public int getIsHg() {
                return isHg;
            }

            public void setIsHg(int isHg) {
                this.isHg = isHg;
            }

            public String getIsHgStr() {
                return isHgStr;
            }

            public void setIsHgStr(String isHgStr) {
                this.isHgStr = isHgStr;
            }

            public double getCoco2() {
                return coco2;
            }

            public void setCoco2(double coco2) {
                this.coco2 = coco2;
            }

            public Date getTestDate() {
                return testDate;
            }

            public void setTestDate(Date testDate) {
                this.testDate = testDate;
            }

            public class IsHgMap {
                private String ishg0;
                private String ishg1;

                public String getIshg0() {
                    return ishg0;
                }

                public void setIshg0(String ishg0) {
                    this.ishg0 = ishg0;
                }

                public String getIshg1() {
                    return ishg1;
                }

                public void setIshg1(String ishg1) {
                    this.ishg1 = ishg1;
                }
            }

            public class SynaStatusMap {
                private String syna0;
                private String syna1;
                private String syna2;
                private String syna3;
                private String syna4;
                private String syna5;
                private String syna6;
                private String syna7;
                private String syna8;
                private String syna9;

                public String getSyna0() {
                    return syna0;
                }

                public void setSyna0(String syna0) {
                    this.syna0 = syna0;
                }

                public String getSyna1() {
                    return syna1;
                }

                public void setSyna1(String syna1) {
                    this.syna1 = syna1;
                }

                public String getSyna2() {
                    return syna2;
                }

                public void setSyna2(String syna2) {
                    this.syna2 = syna2;
                }

                public String getSyna3() {
                    return syna3;
                }

                public void setSyna3(String syna3) {
                    this.syna3 = syna3;
                }

                public String getSyna4() {
                    return syna4;
                }

                public void setSyna4(String syna4) {
                    this.syna4 = syna4;
                }

                public String getSyna5() {
                    return syna5;
                }

                public void setSyna5(String syna5) {
                    this.syna5 = syna5;
                }

                public String getSyna6() {
                    return syna6;
                }

                public void setSyna6(String syna6) {
                    this.syna6 = syna6;
                }

                public String getSyna7() {
                    return syna7;
                }

                public void setSyna7(String syna7) {
                    this.syna7 = syna7;
                }

                public String getSyna8() {
                    return syna8;
                }

                public void setSyna8(String syna8) {
                    this.syna8 = syna8;
                }

                public String getSyna9() {
                    return syna9;
                }

                public void setSyna9(String syna9) {
                    this.syna9 = syna9;
                }
            }

            public class PdjgMap {
                private String pdjg0;
                private String pdjg1;

                public String getPdjg0() {
                    return pdjg0;
                }

                public void setPdjg0(String pdjg0) {
                    this.pdjg0 = pdjg0;
                }

                public String getPdjg1() {
                    return pdjg1;
                }

                public void setPdjg1(String pdjg1) {
                    this.pdjg1 = pdjg1;
                }
            }

            public class StatusMap {
                private String status0;
                private String status1;

                public String getStatus0() {
                    return status0;
                }

                public void setStatus0(String status0) {
                    this.status0 = status0;
                }

                public String getStatus1() {
                    return status1;
                }

                public void setStatus1(String status1) {
                    this.status1 = status1;
                }
            }

            public class NoIsHgMap {
                private String noIsHg0;
                private String noIsHg1;

                public String getNoIsHg0() {
                    return noIsHg0;
                }

                public void setNoIsHg0(String noIsHg0) {
                    this.noIsHg0 = noIsHg0;
                }

                public String getNoIsHg1() {
                    return noIsHg1;
                }

                public void setNoIsHg1(String noIsHg1) {
                    this.noIsHg1 = noIsHg1;
                }
            }

            public class YdzIsHgMap {
                private String ydz0;
                private String ydz1;

                public String getYdz0() {
                    return ydz0;
                }

                public void setYdz0(String ydz0) {
                    this.ydz0 = ydz0;
                }

                public String getYdz1() {
                    return ydz1;
                }

                public void setYdz1(String ydz1) {
                    this.ydz1 = ydz1;
                }
            }

            public class HpysMap {
                private String hpys0;
                private String hpys1;
                private String hpys2;
                private String hpys3;

                public String getHpys0() {
                    return hpys0;
                }

                public void setHpys0(String hpys0) {
                    this.hpys0 = hpys0;
                }

                public String getHpys1() {
                    return hpys1;
                }

                public void setHpys1(String hpys1) {
                    this.hpys1 = hpys1;
                }

                public String getHpys2() {
                    return hpys2;
                }

                public void setHpys2(String hpys2) {
                    this.hpys2 = hpys2;
                }

                public String getHpys3() {
                    return hpys3;
                }

                public void setHpys3(String hpys3) {
                    this.hpys3 = hpys3;
                }
            }

            public class RlzlMap {
                private String rlzl0;
                private String rlzl1;
                private String rlzl2;
                private String rlzl3;
                private String rlzl4;
                private String rlzl5;

                public String getRlzl0() {
                    return rlzl0;
                }

                public void setRlzl0(String rlzl0) {
                    this.rlzl0 = rlzl0;
                }

                public String getRlzl1() {
                    return rlzl1;
                }

                public void setRlzl1(String rlzl1) {
                    this.rlzl1 = rlzl1;
                }

                public String getRlzl2() {
                    return rlzl2;
                }

                public void setRlzl2(String rlzl2) {
                    this.rlzl2 = rlzl2;
                }

                public String getRlzl3() {
                    return rlzl3;
                }

                public void setRlzl3(String rlzl3) {
                    this.rlzl3 = rlzl3;
                }

                public String getRlzl4() {
                    return rlzl4;
                }

                public void setRlzl4(String rlzl4) {
                    this.rlzl4 = rlzl4;
                }

                public String getRlzl5() {
                    return rlzl5;
                }

                public void setRlzl5(String rlzl5) {
                    this.rlzl5 = rlzl5;
                }
            }

            public class JzztMap {
                private String jzzt1;
                private String jzzt2;
                private String jzzt3;

                public String getJzzt1() {
                    return jzzt1;
                }

                public void setJzzt1(String jzzt1) {
                    this.jzzt1 = jzzt1;
                }

                public String getJzzt2() {
                    return jzzt2;
                }

                public void setJzzt2(String jzzt2) {
                    this.jzzt2 = jzzt2;
                }

                public String getJzzt3() {
                    return jzzt3;
                }

                public void setJzzt3(String jzzt3) {
                    this.jzzt3 = jzzt3;
                }
            }

            public class PdStatusMap {
                private String pdStatus0;
                private String pdStatus1;

                public String getPdStatus0() {
                    return pdStatus0;
                }

                public void setPdStatus0(String pdStatus0) {
                    this.pdStatus0 = pdStatus0;
                }

                public String getPdStatus1() {
                    return pdStatus1;
                }

                public void setPdStatus1(String pdStatus1) {
                    this.pdStatus1 = pdStatus1;
                }
            }

            public class PtStatusMap {
                private String ptStatus0;
                private String ptStatus1;

                public String getPtStatus0() {
                    return ptStatus0;
                }

                public void setPtStatus0(String ptStatus0) {
                    this.ptStatus0 = ptStatus0;
                }

                public String getPtStatus1() {
                    return ptStatus1;
                }

                public void setPtStatus1(String ptStatus1) {
                    this.ptStatus1 = ptStatus1;
                }
            }

            public class JzlxMap {
                private String jzlx0;
                private String jzlx1;
                private String jzlx2;

                public String getJzlx0() {
                    return jzlx0;
                }

                public void setJzlx0(String jzlx0) {
                    this.jzlx0 = jzlx0;
                }

                public String getJzlx1() {
                    return jzlx1;
                }

                public void setJzlx1(String jzlx1) {
                    this.jzlx1 = jzlx1;
                }

                public String getJzlx2() {
                    return jzlx2;
                }

                public void setJzlx2(String jzlx2) {
                    this.jzlx2 = jzlx2;
                }
            }

            public class StatusMap1 {
                private String status0;
                private String status1;

                public String getStatus0() {
                    return status0;
                }

                public void setStatus0(String status0) {
                    this.status0 = status0;
                }

                public String getStatus1() {
                    return status1;
                }

                public void setStatus1(String status1) {
                    this.status1 = status1;
                }
            }

            public class PtInfo {
                private JzztMap jzztMap;
                private int jzjcs;
                private PtStatusMap ptStatusMap;
                private String statusStr;
                private String camPort;
                private JzlxMap jzlxMap;
                private StatusMap statusMap;
                private int cdsl;
                private double ddjd;
                private String jzdz;
                private String jzmc;
                private String jzlxStr;
                private String fxlxStr;
                private String ptStatusStr;
                private int id;
                private int ptStatus;
                private String pubKey;
                private double ddwd;
                private String jzbh;
                private long updateTime;
                private int fxlx;
                private int jzlx;
                private String priKey;
                private int verId;
                private String camPass;
                private FxlxMap fxlxMap;
                private String camIp;
                private String jzztStr;
                private String camUserName;
                private int jzzt;
                private int cdpd;
                private long jzrq;
                private int status;

                public JzztMap getJzztMap() {
                    return jzztMap;
                }

                public void setJzztMap(JzztMap jzztMap) {
                    this.jzztMap = jzztMap;
                }

                public int getJzjcs() {
                    return jzjcs;
                }

                public void setJzjcs(int jzjcs) {
                    this.jzjcs = jzjcs;
                }

                public PtStatusMap getPtStatusMap() {
                    return ptStatusMap;
                }

                public void setPtStatusMap(PtStatusMap ptStatusMap) {
                    this.ptStatusMap = ptStatusMap;
                }

                public String getStatusStr() {
                    return statusStr;
                }

                public void setStatusStr(String statusStr) {
                    this.statusStr = statusStr;
                }

                public String getCamPort() {
                    return camPort;
                }

                public void setCamPort(String camPort) {
                    this.camPort = camPort;
                }

                public JzlxMap getJzlxMap() {
                    return jzlxMap;
                }

                public void setJzlxMap(JzlxMap jzlxMap) {
                    this.jzlxMap = jzlxMap;
                }

                public StatusMap getStatusMap() {
                    return statusMap;
                }

                public void setStatusMap(StatusMap statusMap) {
                    this.statusMap = statusMap;
                }

                public int getCdsl() {
                    return cdsl;
                }

                public void setCdsl(int cdsl) {
                    this.cdsl = cdsl;
                }

                public double getDdjd() {
                    return ddjd;
                }

                public void setDdjd(double ddjd) {
                    this.ddjd = ddjd;
                }

                public String getJzdz() {
                    return jzdz;
                }

                public void setJzdz(String jzdz) {
                    this.jzdz = jzdz;
                }

                public String getJzmc() {
                    return jzmc;
                }

                public void setJzmc(String jzmc) {
                    this.jzmc = jzmc;
                }

                public String getJzlxStr() {
                    return jzlxStr;
                }

                public void setJzlxStr(String jzlxStr) {
                    this.jzlxStr = jzlxStr;
                }

                public String getFxlxStr() {
                    return fxlxStr;
                }

                public void setFxlxStr(String fxlxStr) {
                    this.fxlxStr = fxlxStr;
                }

                public String getPtStatusStr() {
                    return ptStatusStr;
                }

                public void setPtStatusStr(String ptStatusStr) {
                    this.ptStatusStr = ptStatusStr;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPtStatus() {
                    return ptStatus;
                }

                public void setPtStatus(int ptStatus) {
                    this.ptStatus = ptStatus;
                }

                public String getPubKey() {
                    return pubKey;
                }

                public void setPubKey(String pubKey) {
                    this.pubKey = pubKey;
                }

                public double getDdwd() {
                    return ddwd;
                }

                public void setDdwd(double ddwd) {
                    this.ddwd = ddwd;
                }

                public String getJzbh() {
                    return jzbh;
                }

                public void setJzbh(String jzbh) {
                    this.jzbh = jzbh;
                }

                public long getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(long updateTime) {
                    this.updateTime = updateTime;
                }

                public int getFxlx() {
                    return fxlx;
                }

                public void setFxlx(int fxlx) {
                    this.fxlx = fxlx;
                }

                public int getJzlx() {
                    return jzlx;
                }

                public void setJzlx(int jzlx) {
                    this.jzlx = jzlx;
                }

                public String getPriKey() {
                    return priKey;
                }

                public void setPriKey(String priKey) {
                    this.priKey = priKey;
                }

                public int getVerId() {
                    return verId;
                }

                public void setVerId(int verId) {
                    this.verId = verId;
                }

                public String getCamPass() {
                    return camPass;
                }

                public void setCamPass(String camPass) {
                    this.camPass = camPass;
                }

                public FxlxMap getFxlxMap() {
                    return fxlxMap;
                }

                public void setFxlxMap(FxlxMap fxlxMap) {
                    this.fxlxMap = fxlxMap;
                }

                public String getCamIp() {
                    return camIp;
                }

                public void setCamIp(String camIp) {
                    this.camIp = camIp;
                }

                public String getJzztStr() {
                    return jzztStr;
                }

                public void setJzztStr(String jzztStr) {
                    this.jzztStr = jzztStr;
                }

                public String getCamUserName() {
                    return camUserName;
                }

                public void setCamUserName(String camUserName) {
                    this.camUserName = camUserName;
                }

                public int getJzzt() {
                    return jzzt;
                }

                public void setJzzt(int jzzt) {
                    this.jzzt = jzzt;
                }

                public int getCdpd() {
                    return cdpd;
                }

                public void setCdpd(int cdpd) {
                    this.cdpd = cdpd;
                }

                public long getJzrq() {
                    return jzrq;
                }

                public void setJzrq(long jzrq) {
                    this.jzrq = jzrq;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public class FxlxMap {
                    private String fxlx1;
                    private String fxlx2;

                    public String getFxlx1() {
                        return fxlx1;
                    }

                    public void setFxlx1(String fxlx1) {
                        this.fxlx1 = fxlx1;
                    }

                    public String getFxlx2() {
                        return fxlx2;
                    }

                    public void setFxlx2(String fxlx2) {
                        this.fxlx2 = fxlx2;
                    }
                }
            }
        }
    }
}
