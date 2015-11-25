<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../Drugstore3_Proxy_SCHEMA_2FOSB_DB_ADAPTERS_2FDrugstore3_ADAPTER_2FXMLSchema_2105222296.xsd"/>
      <rootElement name="getMedicinesNameResponse" namespace="http://ws/"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../OSB_GetMedicine2_Proxy.wsdl"/>
      <rootElement name="getMedicineResponse" namespace="http://xmlns.oracle.com/bpmn/bpmnProcess/OsbWsdlGetMedicines"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.7.0(build 130301.0647.0008) AT [WED NOV 18 09:02:05 PST 2015]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:ns1="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/Medicine"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:ns0="http://xmlns.oracle.com/bpmn/bpmnProcess/OsbWsdlGetMedicines"
                xmlns:SOAP-ENC="http://schemas.xmlsoap.org/soap/encoding/"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:tns="http://ws/"
                xmlns:tns6="http://xmlns.oracle.com/bpm/bpmobject/MyTipes/SetOfMedicines"
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
                exclude-result-prefixes="xsi xsl tns xsd ns1 ns0 SOAP-ENC plnk soap wsdl tns6 xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:template match="/">
    <ns0:getMedicineResponse>
      <tns6:SetOfMedicines>
        <xsl:for-each select="/tns:getMedicinesNameResponse/return">
          <tns6:medicines>
            <ns1:name>
              <xsl:value-of select="name"/>
            </ns1:name>
            <ns1:invima_code>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:invima_code>
            <ns1:record>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:record>
            <ns1:unit>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:unit>
            <ns1:atc>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:atc>
            <ns1:atc_description>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:atc_description>
            <ns1:administration>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:administration>
            <ns1:active_principle>
              <xsl:value-of select="component"/>
            </ns1:active_principle>
            <ns1:pharmaceutical_form>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:pharmaceutical_form>
            <ns1:manufacturer>
              <xsl:text disable-output-escaping="no"></xsl:text>
            </ns1:manufacturer>
            <ns1:drugstore_id>
              <xsl:text disable-output-escaping="no">3</xsl:text>
            </ns1:drugstore_id>
            <ns1:commercial_price>
              <xsl:value-of select="price"/>
            </ns1:commercial_price>
          </tns6:medicines>
        </xsl:for-each>
      </tns6:SetOfMedicines>
    </ns0:getMedicineResponse>
  </xsl:template>
</xsl:stylesheet>
