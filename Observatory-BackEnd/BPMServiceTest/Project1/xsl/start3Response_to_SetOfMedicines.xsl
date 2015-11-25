<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="http://localhost:7001/OTHER_SERVICES/MedicinesSearch/OsbWsdlGetMedicines2_Proxy?wsdl"/>
      <rootElement name="start3Response" namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/OsbWsdlGetMedicines"/>
      <param name="start3Response" />
    </source>
  </mapSources>
  <mapTargets>
    <target type="XSD">
      <schema location="../OsbWsdlRoutingGetMedicines_Proxy_SCHEMA_2FOTHER_SERVICES_2FRoutingMedicineSearch_2FXMLSchema_308386546.xsd"/>
      <rootElement name="SetOfMedicines" namespace="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfMedicines"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.7.0(build 130301.0647.0008) AT [TUE OCT 27 17:46:12 PDT 2015]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:tns6="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfDrugstores"
                xmlns:ns2="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/Medicine"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:bpmo="http://xmlns.oracle.com/bpm/bpmobject/"
                xmlns:tns="http://xmlns.oracle.com/bpmn/bpmnProcess/OsbWsdlGetMedicines"
                xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/Drugstore"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:ns0="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfMedicines"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:tns7="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfMedicinesDrugstore"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl tns6 ns2 tns SOAP-ENC plnk soap wsdl ns1 ns0 tns7 xsd bpmo xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:template match="/">
    <ns0:SetOfMedicines>
      <xsl:for-each select="/tns:start3Response/tns7:SetOfMedicinesDrugstore/tns7:setofmedicines">
        <xsl:for-each select="ns0:medicines">
          <ns0:medicines>
            <ns2:name>
              <xsl:if test="ns2:name/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:name/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:name"/>
            </ns2:name>
            <ns2:invima_code>
              <xsl:if test="ns2:invima_code/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:invima_code/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:invima_code"/>
            </ns2:invima_code>
            <ns2:record>
              <xsl:if test="ns2:record/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:record/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:record"/>
            </ns2:record>
            <ns2:unit>
              <xsl:if test="ns2:unit/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:unit/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:unit"/>
            </ns2:unit>
            <ns2:atc>
              <xsl:if test="ns2:atc/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:atc/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:atc"/>
            </ns2:atc>
            <ns2:atc_description>
              <xsl:if test="ns2:atc_description/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:atc_description/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:atc_description"/>
            </ns2:atc_description>
            <ns2:administration>
              <xsl:if test="ns2:administration/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:administration/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:administration"/>
            </ns2:administration>
            <ns2:active_principle>
              <xsl:if test="ns2:active_principle/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:active_principle/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:active_principle"/>
            </ns2:active_principle>
            <ns2:pharmaceutical_form>
              <xsl:if test="ns2:pharmaceutical_form/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:pharmaceutical_form/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:pharmaceutical_form"/>
            </ns2:pharmaceutical_form>
            <ns2:manufacturer>
              <xsl:if test="ns2:manufacturer/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:manufacturer/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:manufacturer"/>
            </ns2:manufacturer>
            <ns2:drugstore_id>
              <xsl:if test="ns2:drugstore_id/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:drugstore_id/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:drugstore_id"/>
            </ns2:drugstore_id>
            <ns2:commercial_price>
              <xsl:if test="ns2:commercial_price/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="ns2:commercial_price/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="ns2:commercial_price"/>
            </ns2:commercial_price>
          </ns0:medicines>
        </xsl:for-each>
      </xsl:for-each>
    </ns0:SetOfMedicines>
  </xsl:template>
</xsl:stylesheet>