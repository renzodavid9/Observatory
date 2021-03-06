<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="http://soabpm-vm.site:7001/test4/getManufacturers/getManufacturers_Proxy?SCHEMA%2Ftest4%2FgetManufacturers%2FgetManufacturers"/>
      <rootElement name="getManufacturersOutputCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/getManufacturers"/>
      <param name="manufacturersOutputCollection" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../businessCatalog/MyTipes/SetOfManufacturers.xsd"/>
      <rootElement name="SetOfManufacturers" namespace="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfManufacturers"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.7.0(build 130301.0647.0008) AT [SAT NOV 21 15:25:23 PST 2015]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/getManufacturers"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:bpmo="http://xmlns.oracle.com/bpm/bpmobject/"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/Manufacturer"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns2="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfManufacturers"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl ns0 xsd bpmo ns1 ns2 bpws xp20 mhdr bpel oraext dvm hwf med ids bpm xdk xref bpmn ora socket ldap">
  <xsl:template match="/">
    <ns2:SetOfManufacturers>
      <xsl:for-each select="/ns0:getManufacturersOutputCollection/ns0:getManufacturersOutput">
        <ns2:manufacturers>
          <ns1:name>
            <xsl:if test="ns0:name/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:name/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:name"/>
          </ns1:name>
          <ns1:id>
            <xsl:text disable-output-escaping="no"></xsl:text>
          </ns1:id>
          <ns1:tot_medicins>
            <xsl:if test="ns0:tot/@xsi:nil">
              <xsl:attribute name="xsi:nil">
                <xsl:value-of select="ns0:tot/@xsi:nil"/>
              </xsl:attribute>
            </xsl:if>
            <xsl:value-of select="ns0:tot"/>
          </ns1:tot_medicins>
        </ns2:manufacturers>
      </xsl:for-each>
    </ns2:SetOfManufacturers>
  </xsl:template>
</xsl:stylesheet>
