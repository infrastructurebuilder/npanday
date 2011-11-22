#region Apache License, Version 2.0
//
// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
//
#endregion
using System;
using System.Collections.Generic;
using System.Text;


using NPanday.ProjectImporter.Digest;

/// Author: Leopoldo Lee Agdeppa III

namespace NPanday.ProjectImporter.Digest.Model
{


    public class Compile : IncludeBase
    {
        public Compile(string projectBasePath) 
            : base(projectBasePath)
        {
        }


        private string autoGen;
        public string AutoGen
        {
            get { return autoGen; }
            set { autoGen = value; }
        }

        private string designTimeSharedInput;
        public string DesignTimeSharedInput
        {
            get { return designTimeSharedInput; }
            set { designTimeSharedInput = value; }
        }

        private string dependentUpon;
        public string DependentUpon
        {
            get { return dependentUpon; }
            set { dependentUpon = value; }
        }

        private string designTime;
        public string DesignTime
        {
            get { return designTime; }
            set { designTime = value; }
        }

        private string subType;
        public string SubType
        {
            get { return subType; }
            set { subType = value; }
        }
    }
}