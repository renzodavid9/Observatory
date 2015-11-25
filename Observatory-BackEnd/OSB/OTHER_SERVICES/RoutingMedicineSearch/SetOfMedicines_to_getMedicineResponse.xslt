<?xml version="1.0" encoding="UTF-8"?>
<con:xsltEntry xmlns:con="http://www.bea.com/wli/sb/resources/config">
    <con:xslt><![CDATA[<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="XSD">
      <schema location="../xsd/SetOfMedicines.xsd"/>
      <rootElement name="SetOfMedicines" namespace="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfMedicines"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../OsbWsdlGetMedicines.wsdl"/>
      <rootElement name="getMedicineResponse" namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/OsbWsdlGetMedicines"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.7.0(build 130301.0647.0008) AT [SUN NOV 01 12:27:36 PST 2015]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/Medicine"
                xmlns:bpmo="http://xmlns.oracle.com/bpm/bpmobject/"
                xmlns:tns="http://xmlns.oracle.com/bpmn/bpmnProcess/OsbWsdlGetMedicines"
                xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:wsdlsoap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns0="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfMedicines"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl ns1 bpmo ns0 xsd tns SOAP-ENC plnk wsdlsoap wsdl xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:template match="/">
    <tns:getMedicineResponse>
      <ns0:SetOfMedicines>
        <xsl:for-each select="/ns0:SetOfMedicines/ns0:medicines">
          <ns0:medicines>
            <ns1:name>
              <xsl:if test="ns1:name/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:name/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:name"/>
            </ns1:name>
            <ns1:invima_code>
              <xsl:if test="ns1:invima_code/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:invima_code/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:invima_code"/>
            </ns1:invima_code>
            <ns1:record>
              <xsl:if test="ns1:record/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:record/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:record"/>
            </ns1:record>
            <ns1:unit>
              <xsl:if test="ns1:unit/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:unit/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:unit"/>
            </ns1:unit>
            <ns1:atc>
              <xsl:if test="ns1:atc/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:atc/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:atc"/>
            </ns1:atc>
            <ns1:atc_description>
              <xsl:if test="ns1:atc_description/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:atc_description/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:atc_description"/>
            </ns1:atc_description>
            <ns1:administration>
              <xsl:if test="ns1:administration/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:administration/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:administration"/>
            </ns1:administration>
            <ns1:active_principle>
              <xsl:if test="ns1:active_principle/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:active_principle/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:active_principle"/>
            </ns1:active_principle>
            <ns1:pharmaceutical_form>
              <xsl:if test="ns1:pharmaceutical_form/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:pharmaceutical_form/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:pharmaceutical_form"/>
            </ns1:pharmaceutical_form>
            <ns1:manufacturer>
              <xsl:if test="ns1:manufacturer/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:manufacturer/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:manufacturer"/>
            </ns1:manufacturer>
            <ns1:drugstore_id>
              <xsl:if test="ns1:drugstore_id/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:drugstore_id/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:drugstore_id"/>
            </ns1:drugstore_id>
            <ns1:commercial_price>
              <xsl:if test="ns1:commercial_price/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns1:commercial_price/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns1:commercial_price"/>
            </ns1:commercial_price>
          </ns0:medicines>
        </xsl:for-each>
      </ns0:SetOfMedicines>
    </tns:getMedicineResponse>
  </xsl:template>
</xsl:stylesheet>]]></con:xslt>
</con:xsltEntry>