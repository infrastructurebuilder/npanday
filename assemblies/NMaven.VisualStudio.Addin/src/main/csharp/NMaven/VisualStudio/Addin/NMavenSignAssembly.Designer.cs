namespace NMaven.VisualStudio.Addin
{
    partial class NMavenSignAssembly
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnBrowse = new System.Windows.Forms.Button();
            this.lblBrowseAssemblySignKey = new System.Windows.Forms.Label();
            this.txtBrowseAssemblySignKey = new System.Windows.Forms.TextBox();
            this.btnCancel = new System.Windows.Forms.Button();
            this.btnOk = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // btnBrowse
            // 
            this.btnBrowse.Location = new System.Drawing.Point(571, 9);
            this.btnBrowse.Name = "btnBrowse";
            this.btnBrowse.Size = new System.Drawing.Size(107, 28);
            this.btnBrowse.TabIndex = 9;
            this.btnBrowse.Text = "&Browse";
            this.btnBrowse.UseVisualStyleBackColor = true;
            this.btnBrowse.Click += new System.EventHandler(this.btnBrowse_Click);
            // 
            // lblBrowseAssemblySignKey
            // 
            this.lblBrowseAssemblySignKey.AutoSize = true;
            this.lblBrowseAssemblySignKey.Location = new System.Drawing.Point(12, 12);
            this.lblBrowseAssemblySignKey.Name = "lblBrowseAssemblySignKey";
            this.lblBrowseAssemblySignKey.Size = new System.Drawing.Size(132, 17);
            this.lblBrowseAssemblySignKey.TabIndex = 8;
            this.lblBrowseAssemblySignKey.Text = "Assembly Sign Key:";
            // 
            // txtBrowseAssemblySignKey
            // 
            this.txtBrowseAssemblySignKey.Location = new System.Drawing.Point(150, 12);
            this.txtBrowseAssemblySignKey.Name = "txtBrowseAssemblySignKey";
            this.txtBrowseAssemblySignKey.Size = new System.Drawing.Size(415, 22);
            this.txtBrowseAssemblySignKey.TabIndex = 7;
            // 
            // btnCancel
            // 
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Location = new System.Drawing.Point(593, 46);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(85, 25);
            this.btnCancel.TabIndex = 6;
            this.btnCancel.Text = "&Cancel";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // btnOk
            // 
            this.btnOk.Location = new System.Drawing.Point(497, 46);
            this.btnOk.Name = "btnOk";
            this.btnOk.Size = new System.Drawing.Size(90, 25);
            this.btnOk.TabIndex = 5;
            this.btnOk.Text = "&OK";
            this.btnOk.UseVisualStyleBackColor = true;
            this.btnOk.Click += new System.EventHandler(this.btnOk_Click);
            // 
            // NMavenSignAssembly
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(693, 92);
            this.ControlBox = false;
            this.Controls.Add(this.btnBrowse);
            this.Controls.Add(this.lblBrowseAssemblySignKey);
            this.Controls.Add(this.txtBrowseAssemblySignKey);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnOk);
            this.Name = "NMavenSignAssembly";
            this.ShowInTaskbar = false;
            this.Text = "Set NMaven Assembly Sign Key";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnBrowse;
        private System.Windows.Forms.Label lblBrowseAssemblySignKey;
        private System.Windows.Forms.TextBox txtBrowseAssemblySignKey;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Button btnOk;
    }
}