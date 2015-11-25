<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../businessCatalog/MyTipes/Drugstore.xsd"/>
      <rootElement name="Drugstore" namespace="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/Drugstore"/>
      <param name="temp_drugstore" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../businessCatalog/MyTipes/SetOfDrugstores.xsd"/>
      <rootElement name="SetOfDrugstores" namespace="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfDrugstores"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.7.0(build 130301.0647.0008) AT [SUN OCT 25 13:01:52 PDT 2015]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfDrugstores"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:bpmo="http://xmlns.oracle.com/bpm/bpmobject/"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ns0="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/Drugstore"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl bpmo xsd ns0 ns1 bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:template match="/">
    <ns1:SetOfDrugstores>
      <xsl:for-each select="/ns0:Drugstore">
        <ns1:drugstores>
          <ns0:id>
            <xsl:if test="ns0:id/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:id/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:id"/>
          </ns0:id>
          <ns0:name>
            <xsl:if test="ns0:name/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:name/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:name"/>
          </ns0:name>
          <ns0:shortname>
            <xsl:if test="ns0:shortname/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:shortname/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:shortname"/>
          </ns0:shortname>
          <ns0:latitude>
            <xsl:if test="ns0:latitude/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:latitude/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:latitude"/>
          </ns0:latitude>
          <ns0:longitude>
            <xsl:if test="ns0:longitude/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:longitude/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:longitude"/>
          </ns0:longitude>
        </ns1:drugstores>
      </xsl:for-each>
    </ns1:SetOfDrugstores>
  </xsl:template>
</xsl:stylesheet>
