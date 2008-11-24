using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;

/// Author: Leopoldo Lee Agdeppa III

namespace NMaven.ProjectImporter.Parser.VisualStudioProjectTypes
{
    public class VisualStudioProjectType
    {
        static Dictionary<string, VisualStudioProjectTypeEnum> __visualStudioProjectTypes;
        static Dictionary<VisualStudioProjectTypeEnum, string> __visualStudioProjectTypeGuids;
        static VisualStudioProjectType()
        {
            __visualStudioProjectTypes = new Dictionary<string, VisualStudioProjectTypeEnum>();
            __visualStudioProjectTypeGuids = new Dictionary<VisualStudioProjectTypeEnum, string>();


            //Windows (C#)	 {FAE04EC0-301F-11D3-BF4B-00C04F79EFBC}
            __visualStudioProjectTypes.Add("FAE04EC0-301F-11D3-BF4B-00C04F79EFBC", VisualStudioProjectTypeEnum.Windows__CSharp);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Windows__CSharp, "FAE04EC0-301F-11D3-BF4B-00C04F79EFBC");


            //Windows (VB.NET)	 {F184B08F-C81C-45F6-A57F-5ABD9991F28F}
            __visualStudioProjectTypes.Add("F184B08F-C81C-45F6-A57F-5ABD9991F28F", VisualStudioProjectTypeEnum.Windows__VbDotNet);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Windows__VbDotNet, "F184B08F-C81C-45F6-A57F-5ABD9991F28F");


            //Windows (Visual C++)	 {8BC9CEB8-8B4A-11D0-8D11-00A0C91BC942}
            __visualStudioProjectTypes.Add("8BC9CEB8-8B4A-11D0-8D11-00A0C91BC942", VisualStudioProjectTypeEnum.Windows__VCpp);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Windows__VCpp, "8BC9CEB8-8B4A-11D0-8D11-00A0C91BC942");


            //Web Application	 {349C5851-65DF-11DA-9384-00065B846F21}
            __visualStudioProjectTypes.Add("349C5851-65DF-11DA-9384-00065B846F21", VisualStudioProjectTypeEnum.Web_Application);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Web_Application, "349C5851-65DF-11DA-9384-00065B846F21");


            //Web Site	 {E24C65DC-7377-472B-9ABA-BC803B73C61A}
            __visualStudioProjectTypes.Add("E24C65DC-7377-472B-9ABA-BC803B73C61A", VisualStudioProjectTypeEnum.Web_Site);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Web_Site, "E24C65DC-7377-472B-9ABA-BC803B73C61A");


            //Distributed System	 {F135691A-BF7E-435D-8960-F99683D2D49C}
            __visualStudioProjectTypes.Add("F135691A-BF7E-435D-8960-F99683D2D49C", VisualStudioProjectTypeEnum.Distributed_System);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Distributed_System, "F135691A-BF7E-435D-8960-F99683D2D49C");


            //Windows Communication Foundation (WCF)	 {3D9AD99F-2412-4246-B90B-4EAA41C64699}
            __visualStudioProjectTypes.Add("3D9AD99F-2412-4246-B90B-4EAA41C64699", VisualStudioProjectTypeEnum.Windows_Communication_Foundation__WCF);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Windows_Communication_Foundation__WCF, "3D9AD99F-2412-4246-B90B-4EAA41C64699");


            //Windows Presentation Foundation (WPF)	 {60DC8134-EBA5-43B8-BCC9-BB4BC16C2548}
            __visualStudioProjectTypes.Add("60DC8134-EBA5-43B8-BCC9-BB4BC16C2548", VisualStudioProjectTypeEnum.Windows_Presentation_Foundation__WPF);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Windows_Presentation_Foundation__WPF, "60DC8134-EBA5-43B8-BCC9-BB4BC16C2548");


            //Visual Database Tools	 {C252FEB5-A946-4202-B1D4-9916A0590387}
            __visualStudioProjectTypes.Add("C252FEB5-A946-4202-B1D4-9916A0590387", VisualStudioProjectTypeEnum.Visual_Database_Tools);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Visual_Database_Tools, "C252FEB5-A946-4202-B1D4-9916A0590387");


            //Database	 {A9ACE9BB-CECE-4E62-9AA4-C7E7C5BD2124}
            __visualStudioProjectTypes.Add("A9ACE9BB-CECE-4E62-9AA4-C7E7C5BD2124", VisualStudioProjectTypeEnum.Database);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Database, "A9ACE9BB-CECE-4E62-9AA4-C7E7C5BD2124");


            //Database (other project types)	 {4F174C21-8C12-11D0-8340-0000F80270F8}
            __visualStudioProjectTypes.Add("4F174C21-8C12-11D0-8340-0000F80270F8", VisualStudioProjectTypeEnum.Database__other_project_types);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Database__other_project_types, "4F174C21-8C12-11D0-8340-0000F80270F8");
            

            //Test	 {3AC096D0-A1C2-E12C-1390-A8335801FDAB}
            __visualStudioProjectTypes.Add("3AC096D0-A1C2-E12C-1390-A8335801FDAB", VisualStudioProjectTypeEnum.Test);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Test, "3AC096D0-A1C2-E12C-1390-A8335801FDAB");


            //Legacy (2003) Smart Device (C#)	 {20D4826A-C6FA-45DB-90F4-C717570B9F32}
            __visualStudioProjectTypes.Add("20D4826A-C6FA-45DB-90F4-C717570B9F32", VisualStudioProjectTypeEnum.Legacy__2003_Smart_Device__CSharp);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Legacy__2003_Smart_Device__CSharp, "20D4826A-C6FA-45DB-90F4-C717570B9F32");


            //Legacy (2003) Smart Device (VB.NET)	 {CB4CE8C6-1BDB-4DC7-A4D3-65A1999772F8}
            __visualStudioProjectTypes.Add("CB4CE8C6-1BDB-4DC7-A4D3-65A1999772F8", VisualStudioProjectTypeEnum.Legacy__2003_Smart_Device__VbDotNet);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Legacy__2003_Smart_Device__VbDotNet, "CB4CE8C6-1BDB-4DC7-A4D3-65A1999772F8");


            //Smart Device (C#)	 {4D628B5B-2FBC-4AA6-8C16-197242AEB884}
            __visualStudioProjectTypes.Add("4D628B5B-2FBC-4AA6-8C16-197242AEB884", VisualStudioProjectTypeEnum.Smart_Device__CSharp);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Smart_Device__CSharp, "4D628B5B-2FBC-4AA6-8C16-197242AEB884");


            //Smart Device (VB.NET)	 {68B1623D-7FB9-47D8-8664-7ECEA3297D4F}
            __visualStudioProjectTypes.Add("68B1623D-7FB9-47D8-8664-7ECEA3297D4F", VisualStudioProjectTypeEnum.Smart_Device__VbDotNet);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Smart_Device__VbDotNet, "68B1623D-7FB9-47D8-8664-7ECEA3297D4F");


            //Workflow (C#)	 {14822709-B5A1-4724-98CA-57A101D1B079}
            __visualStudioProjectTypes.Add("14822709-B5A1-4724-98CA-57A101D1B079", VisualStudioProjectTypeEnum.Workflow__CSharp);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Workflow__CSharp, "14822709-B5A1-4724-98CA-57A101D1B079");


            //Workflow (VB.NET)	 {D59BE175-2ED0-4C54-BE3D-CDAA9F3214C8}
            __visualStudioProjectTypes.Add("D59BE175-2ED0-4C54-BE3D-CDAA9F3214C8", VisualStudioProjectTypeEnum.Workflow__VbDotNet);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Workflow__VbDotNet, "D59BE175-2ED0-4C54-BE3D-CDAA9F3214C8");


            //Deployment Merge Module	 {06A35CCD-C46D-44D5-987B-CF40FF872267}
            __visualStudioProjectTypes.Add("06A35CCD-C46D-44D5-987B-CF40FF872267", VisualStudioProjectTypeEnum.Deployment_Merge_Module);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Deployment_Merge_Module, "06A35CCD-C46D-44D5-987B-CF40FF872267");


            //Deployment Cab	 {3EA9E505-35AC-4774-B492-AD1749C4943A}
            __visualStudioProjectTypes.Add("3EA9E505-35AC-4774-B492-AD1749C4943A", VisualStudioProjectTypeEnum.Deployment_Cab);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Deployment_Cab, "3EA9E505-35AC-4774-B492-AD1749C4943A");


            //Deployment Setup	 {978C614F-708E-4E1A-B201-565925725DBA}
            __visualStudioProjectTypes.Add("978C614F-708E-4E1A-B201-565925725DBA", VisualStudioProjectTypeEnum.Deployment_Setup);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Deployment_Setup, "978C614F-708E-4E1A-B201-565925725DBA");


            //Deployment Smart Device Cab	 {AB322303-2255-48EF-A496-5904EB18DA55}
            __visualStudioProjectTypes.Add("AB322303-2255-48EF-A496-5904EB18DA55", VisualStudioProjectTypeEnum.Deployment_Smart_Device_Cab);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Deployment_Smart_Device_Cab, "AB322303-2255-48EF-A496-5904EB18DA55");


            //Visual Studio Tools for Applications (VSTA)	 {A860303F-1F3F-4691-B57E-529FC101A107}
            __visualStudioProjectTypes.Add("A860303F-1F3F-4691-B57E-529FC101A107", VisualStudioProjectTypeEnum.Visual_Studio_Tools_for_Applications__VSTA);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Visual_Studio_Tools_for_Applications__VSTA, "A860303F-1F3F-4691-B57E-529FC101A107");


            //Visual Studio Tools for Office (VSTO)	 {BAA0C2D2-18E2-41B9-852F-F413020CAA33}
            __visualStudioProjectTypes.Add("BAA0C2D2-18E2-41B9-852F-F413020CAA33", VisualStudioProjectTypeEnum.Visual_Studio_Tools_for_Office__VSTO);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.Visual_Studio_Tools_for_Office__VSTO, "BAA0C2D2-18E2-41B9-852F-F413020CAA33");


            //SharePoint Workflow	 {F8810EC1-6754-47FC-A15F-DFABD2E3FA90}
            __visualStudioProjectTypes.Add("F8810EC1-6754-47FC-A15F-DFABD2E3FA90", VisualStudioProjectTypeEnum.SharePoint_Workflow);
            __visualStudioProjectTypeGuids.Add(VisualStudioProjectTypeEnum.SharePoint_Workflow, "F8810EC1-6754-47FC-A15F-DFABD2E3FA90");



        }

        /// <summary>
        /// Gets the VisualStudioProjectTypeEnum of the given GUID (Global Unique Identifier)
        /// </summary>
        /// <param name="guid">VisualStudio Project Type GUID</param>
        /// <returns>VisualStudioProjectTypeEnum equivalent of the GUID</returns>
        public static VisualStudioProjectTypeEnum GetVisualStudioProjectType(string guid)
        {

            string strGuid = guid.Replace("{", "");
            strGuid = strGuid.Replace("}", "");


            VisualStudioProjectTypeEnum projectType = 0;

            foreach (string guidItem in strGuid.Split(';'))
            {
                try
                {
                    projectType |= __visualStudioProjectTypes[guidItem.ToUpper()];
                }
                catch
                {
                    throw new NotSupportedException("Unknown Project Type GUID: " + guidItem);
                }
            }

            return projectType;
        }

        public static string GetVisualStudioProjectTypeGuid(VisualStudioProjectTypeEnum visualStudioProjectTypeEnum)
        {
            List<string> list = new List<string>();
            foreach (VisualStudioProjectTypeEnum value in Enum.GetValues(typeof(VisualStudioProjectTypeEnum)))
            {
                if ((visualStudioProjectTypeEnum & value) == value)
                {
                    list.Add("{" + __visualStudioProjectTypeGuids[value] + "}");
                }
            }

            return string.Join(";", list.ToArray());
        }
    }
}