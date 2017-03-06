//+======================================================================
//
// Project:   Tango
//
// Description:  Basic Dialog Class to select files and directory fo code generation.
//
// $Author: verdier $
//
// Copyright (C) :      2004,2005,2006,2007,2008,2009,2009,2010,2011,2012,2013,2014
//						European Synchrotron Radiation Facility
//                      BP 220, Grenoble 38043
//                      FRANCE
//
// This file is part of Tango.
//
// Tango is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// Tango is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with Tango.  If not, see <http://www.gnu.org/licenses/>.
//
// $Revision: $
// $Date:  $
//
// $HeadURL: $
//
//-======================================================================

package org.tango.pogo.pogo_gui;


import fr.esrf.tango.pogo.pogoDsl.AdditionalFile;
import fr.esrf.tango.pogo.pogoDsl.ClassDescription;
import fr.esrf.tango.pogo.pogoDsl.Inheritance;
import fr.esrf.tango.pogo.pogoDsl.PogoDeviceClass;
import fr.esrf.tango.pogo.pogoDsl.PogoMultiClasses;
import fr.esrf.tangoatk.widget.util.ATKGraphicsUtils;

import org.eclipse.emf.common.util.EList;
import org.tango.pogo.pogo_gui.tools.PogoFileFilter;
import org.tango.pogo.pogo_gui.tools.PogoProperty;
import org.tango.pogo.pogo_gui.tools.Utils;

import javax.swing.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Dialog object to manage generation preferences.
 */

@SuppressWarnings("MagicConstant")
public class GenerateDialog extends JDialog {
    private int mode = PogoConst.SINGLE_CLASS;
    private static int returnStatus;
    private DeviceClass deviceClass;
    private List<JRadioButton> radioButtons = new ArrayList<>();
    private boolean cmakeAvailable = false;
    //===================================================================
    /**
     * Initializes the Form
     *
     * @param parent the parent object
     */
    //===================================================================
    public GenerateDialog(JFrame parent) {
        super(parent, true);
        initComponents();

        radioButtons.add(xmiBtn);
        radioButtons.add(codeBtn);
        radioButtons.add(makefileBtn);
        radioButtons.add(cMakeListsBtn);
        radioButtons.add(vc10Btn);
        radioButtons.add(vc12Btn);
        radioButtons.add(pyHlProjectBtn);
        radioButtons.add(prPythonHLBtn);
        radioButtons.add(projectBtn);
        radioButtons.add(sphinxBtn);
        radioButtons.add(htmlBtn);

        //  Check if cmake available (cmake_tango.opt file cane be found)
        String path = PogoProperty.makefileHome;
        String fileName = "/cmake_tango.opt";
        String env = System.getProperty("DEBUG_MAKE");
        if (env!=null) {
            path = env;
        }
        else {
            String str = "$(TANGO_HOME)";
            if (fileName.startsWith(str)) {
                String th = System.getenv("TANGO_HOME");
                if (th == null)
                    th = System.getProperty("TANGO_HOME");
                if (th != null)
                    path = th;
            }
        }
        cmakeAvailable = new File(path+'/'+fileName).exists();

        //  Check debug
        String dbg = System.getenv("DEBUG_MAKE");
        if (Utils.isTrue(dbg)) {
            codeBtn.setSelected(false);
            makefileBtn.setSelected(true);
        }
        pack();
        ATKGraphicsUtils.centerDialog(this);
    }

    //===================================================================
    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the FormEditor.
     */
    //===================================================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JPanel bottomPanel = new javax.swing.JPanel();
        javax.swing.JButton okBtn = new javax.swing.JButton();
        javax.swing.JButton cancelBtn = new javax.swing.JButton();
        javax.swing.JPanel pathPanel = new javax.swing.JPanel();
        javax.swing.JLabel nameLbl = new javax.swing.JLabel();
        outPathText = new javax.swing.JTextField();
        javax.swing.JButton browseBtn = new javax.swing.JButton();
        javax.swing.JPanel buttonsPanel = new javax.swing.JPanel();
        xmiBtn = new javax.swing.JRadioButton();
        codeBtn = new javax.swing.JRadioButton();
        prPythonHLBtn = new javax.swing.JRadioButton();
        makefileBtn = new javax.swing.JRadioButton();
        cMakeListsBtn = new javax.swing.JRadioButton();
        javax.swing.JLabel generateLabel = new javax.swing.JLabel();
        htmlBtn = new javax.swing.JRadioButton();
        warningPanel = new javax.swing.JPanel();
        warningLabel = new javax.swing.JLabel();
        javax.swing.JLabel dummyLbl = new javax.swing.JLabel();
        javax.swing.JButton detailsBtn = new javax.swing.JButton();
        vc10Btn = new javax.swing.JRadioButton();
        vc12Btn = new javax.swing.JRadioButton();
        windowsLabel = new javax.swing.JLabel();
        linuxLabel = new javax.swing.JLabel();
        docLabel = new javax.swing.JLabel();
        javax.swing.JLabel classLabel = new javax.swing.JLabel();
        javaProjectLabel = new javax.swing.JLabel();
        projectBtn = new javax.swing.JRadioButton();
        pyHlProjectBtn = new javax.swing.JRadioButton();
        sphinxBtn = new javax.swing.JRadioButton();

        setTitle("Generation Preference  Window");
        setBackground(new java.awt.Color(198, 178, 168));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        bottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        okBtn.setText("OK");
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(okBtn);

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(cancelBtn);

        getContentPane().add(bottomPanel, java.awt.BorderLayout.SOUTH);

        pathPanel.setLayout(new java.awt.GridBagLayout());

        nameLbl.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        nameLbl.setText("Output Path :    ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        pathPanel.add(nameLbl, gridBagConstraints);

        outPathText.setMinimumSize(new java.awt.Dimension(450, 25));
        outPathText.setPreferredSize(new java.awt.Dimension(450, 25));
        outPathText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });
        outPathText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                outPathTextKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        pathPanel.add(outPathText, gridBagConstraints);

        browseBtn.setText("Browse");
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 40, 0);
        pathPanel.add(browseBtn, gridBagConstraints);

        getContentPane().add(pathPanel, java.awt.BorderLayout.NORTH);

        buttonsPanel.setLayout(new java.awt.GridBagLayout());

        xmiBtn.setSelected(true);
        xmiBtn.setText("XMI   file");
        xmiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmiBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(xmiBtn, gridBagConstraints);

        codeBtn.setSelected(true);
        codeBtn.setText("Code files");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(codeBtn, gridBagConstraints);

        prPythonHLBtn.setSelected(true);
        prPythonHLBtn.setText("Protected Regions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(prPythonHLBtn, gridBagConstraints);

        makefileBtn.setText("Makefile");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(makefileBtn, gridBagConstraints);

        cMakeListsBtn.setText("CMakeLists");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(cMakeListsBtn, gridBagConstraints);

        generateLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        generateLabel.setText("Files to be generated :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(generateLabel, gridBagConstraints);

        htmlBtn.setText("html Pages");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(htmlBtn, gridBagConstraints);

        warningPanel.setBackground(new java.awt.Color(255, 229, 126));

        warningLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        warningLabel.setText("  class  is  abstract !!!");
        warningPanel.add(warningLabel);

        dummyLbl.setText("             ");
        warningPanel.add(dummyLbl);

        detailsBtn.setText("Details");
        detailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBtnActionPerformed(evt);
            }
        });
        warningPanel.add(detailsBtn);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 0, 0);
        buttonsPanel.add(warningPanel, gridBagConstraints);

        vc10Btn.setText("VC10 Project");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(vc10Btn, gridBagConstraints);

        vc12Btn.setText("VC12 Project");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(vc12Btn, gridBagConstraints);

        windowsLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        windowsLabel.setText("Windows:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        buttonsPanel.add(windowsLabel, gridBagConstraints);

        linuxLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        linuxLabel.setText("Linux:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        buttonsPanel.add(linuxLabel, gridBagConstraints);

        docLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        docLabel.setText("Documentation:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        buttonsPanel.add(docLabel, gridBagConstraints);

        classLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        classLabel.setText("Device Class:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        buttonsPanel.add(classLabel, gridBagConstraints);

        javaProjectLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        javaProjectLabel.setText("Projects:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 0);
        buttonsPanel.add(javaProjectLabel, gridBagConstraints);

        projectBtn.setText("Eclipse/Pom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(projectBtn, gridBagConstraints);

        pyHlProjectBtn.setText("Python Package");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(pyHlProjectBtn, gridBagConstraints);

        sphinxBtn.setText("Sphinx");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        buttonsPanel.add(sphinxBtn, gridBagConstraints);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    //=============================================================
    /**
     * Open a File Chooser Dialog to select the new output directory.
     *
     * @param evt the action event object
     */
    //=============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void browsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browsActionPerformed

        JFileChooser chooser = new JFileChooser(outPathText.getText());
        chooser.addChoosableFileFilter(new PogoFileFilter("", "Directory"));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int retval = chooser.showDialog(this, "Target Dir.");

        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (file != null)
                if (file.isDirectory()) {
                    outPathText.setText(file.getAbsolutePath());
                }
        }
        outPathText.requestFocus();
    }//GEN-LAST:event_browsActionPerformed


    //=============================================================
    //=============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed

        //	Check if path exists and if it exists, if it is a directory
        String path = outPathText.getText();
        File file = new File(path);
        if (file.exists()) {
            if (file.isDirectory()) {
                //  Special check for Makefile
                if (makefileBtn.isSelected() || cMakeListsBtn.isSelected() ||
                        vc12Btn.isSelected() || vc10Btn.isSelected()) {
                    
                    int ret = manageMakefile();
                    if (ret==JOptionPane.OK_OPTION)
                        if (vc10Btn.isSelected())
                            ret = manageWindowsPathCase();
                    doClose(ret); //  Close dialog if OK
                }
                //  Special case for pom.xml (maven structure path expected)
                if (deviceClass!=null) {
	                PogoDeviceClass pogoDeviceClass = deviceClass.getPogoDeviceClass();
	                if (pogoDeviceClass!=null) {
	                	ClassDescription classDescription = pogoDeviceClass.getDescription();
	                	if (classDescription!=null) {
			                int lang = Utils.getLanguage(classDescription.getLanguage());
			                if (lang==PogoConst.Java && projectBtn.isSelected()) {
			                    if (checkMavenPathCase(file.getAbsolutePath()))
			                        doClose(JOptionPane.OK_OPTION);
			                    else
			                        return;
			                }
	                	}
	                }
                }
                doClose(JOptionPane.OK_OPTION);
            } else
                JOptionPane.showMessageDialog(this,
                        path + " is not a directory !",
                        "Error Window",
                        JOptionPane.ERROR_MESSAGE);
        } else
        if (JOptionPane.showConfirmDialog(this,
                        path + " Does not exists !\n\n" +
                                "   Would you like to create it ? ",
                        "Error Window",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
            //	The try to create a new directory
            try {
                if (file.mkdir()) {
                    System.out.println(path + " created");
                    doClose(JOptionPane.OK_OPTION);
                } else
                    JOptionPane.showMessageDialog(this,
                            "Cannot create: " + path,
                            "Error Window",
                            JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Cannot create: " + path + "\n" + e,
                        "Error Window",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_okBtnActionPerformed

    //=============================================================
    /**
     * for maven source file must be generated in path
     * ending by src/main/java.
     * pom.xml will be generated in source dir +  ../../..
     * @return true if path ok
     */
    //=============================================================
    private boolean checkMavenPathCase(String path) {
        if (path.endsWith("src/main/java"))
            return true;
        if (path.endsWith("src\\main\\java")) // Win case
            return true;
        JOptionPane.showMessageDialog(this,
                "pom.xml file needs maven structure\n"+
                "The source files must be generated in path like --/--/src/main/java/\n" +
                "pom.xml will be generated on top of /src/ directory.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
   //=============================================================
    /**
     * If windows project generated under Linux,
     * Inheritance path must be a valid Windows path
     * @return OK or CANCEL
     */
    //=============================================================
    private int manageWindowsPathCase() {
       if (Utils.osIsUnix()) {
           EList<Inheritance> inheritances =
                   deviceClass.getPogoDeviceClass().getDescription().getInheritances();
           for (Inheritance inheritance : inheritances) {
               if (!DeviceClass.isDefaultInheritance(inheritance)) {
                   String path = inheritance.getSourcePath();
                   String newPath = (String) JOptionPane.showInputDialog(this,
                           "Inheritance class path for "+
                           inheritance.getClassname() + "  is NOT a WINDOWS path !   "+
                           "Please give a valid Windows path.",
                           "Input Dialog",
                           JOptionPane.INFORMATION_MESSAGE,
                           null, null, path);
                   if (newPath==null)
                      return JOptionPane.CANCEL_OPTION;
                  else
                      inheritance.setSourcePath(newPath);
               }
           }
       }
       
       return JOptionPane.OK_OPTION;
    }

    //=============================================================
    //=============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        doClose(JOptionPane.CANCEL_OPTION);
    }//GEN-LAST:event_cancelBtnActionPerformed

    //=============================================================
    //=============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(JOptionPane.CANCEL_OPTION);
    }//GEN-LAST:event_closeDialog

    //=============================================================
    //=============================================================
    private String buidDetailsString(List<String> items, String name) {
        StringBuilder sb = new StringBuilder();

        if (items.size() > 0) {
            if (items.size() > 1) {
                sb.append("The following ").append(name).append("s are abstract:\n");
                for (String s : items)
                    sb.append("  - ").append(s).append("\n");
            } else
                sb.append("The ").append(name).append("  ").append(items.get(0)).append("  is abstract.\n");
        }
        return sb.toString();
    }

    //=============================================================
    //=============================================================
    @SuppressWarnings({"UnusedDeclaration"})
    private void detailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBtnActionPerformed

        List<String> commands = deviceClass.getAbstractCommandNames();
        List<String> attributes = deviceClass.getAbstractAttributeNames();

        String
                message = buidDetailsString(commands, "command");
        message += "\n";
        message += buidDetailsString(attributes, "attribute");

        JOptionPane.showMessageDialog(this,
                message,
                "Details Window", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_detailsBtnActionPerformed

    //======================================================
    //======================================================
    private void outPathTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_outPathTextKeyPressed
        if (evt.getKeyCode() == 27) {  //  Escape
            doClose(JOptionPane.CANCEL_OPTION);
        }
    }//GEN-LAST:event_outPathTextKeyPressed

    //======================================================
    //======================================================
    @SuppressWarnings("UnusedParameters")
    private void xmiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmiBtnActionPerformed
        //  XMI must be generated every time
        xmiBtn.setSelected(true);
    }//GEN-LAST:event_xmiBtnActionPerformed

    //======================================================
    //======================================================
    public int showDialog(DeviceClass devclass) {
        mode = PogoConst.SINGLE_CLASS;
        this.deviceClass = devclass;
        String path = devclass.getPogoDeviceClass().getDescription().getSourcePath();
        if (path == null || !new File(path).exists())
            path = PogoGUI.homeDir;
        outPathText.setText(path);
        outPathText.setRequestFocusEnabled(true);
        outPathText.requestFocus();

        if (devclass.checkIfAbstractClass()) {
            makefileBtn.setEnabled(false);
            cMakeListsBtn.setEnabled(false);
            vc10Btn.setEnabled(false);
            vc12Btn.setEnabled(false);
            javaProjectLabel.setVisible(false);
            projectBtn.setVisible(false);
            pyHlProjectBtn.setVisible(false);
            prPythonHLBtn.setVisible(false);
            sphinxBtn.setVisible(false);
        }
        int lang = Utils.getLanguage(devclass.getPogoDeviceClass().getDescription().getLanguage());
        switch (lang) {
            case PogoConst.Cpp:
                makefileBtn.setVisible(true);
                cMakeListsBtn.setVisible(cmakeAvailable);
                windowsLabel.setVisible(true);
                linuxLabel.setVisible(true);
                vc10Btn.setVisible(false);  //  Not managed anymore
                vc12Btn.setVisible(true);
                javaProjectLabel.setVisible(true);
                projectBtn.setText("Eclipse Project");
                projectBtn.setVisible(true);
                pyHlProjectBtn.setVisible(false);
                prPythonHLBtn.setVisible(false);
                sphinxBtn.setVisible(false);
                break;
            case PogoConst.Java:
                makefileBtn.setVisible(true);
                cMakeListsBtn.setVisible(false);
                windowsLabel.setVisible(false);
                linuxLabel.setVisible(true);
                vc10Btn.setVisible(false);
                vc12Btn.setVisible(false);
                javaProjectLabel.setVisible(true);
                projectBtn.setText("pom.xml");
                projectBtn.setVisible(true);
                pyHlProjectBtn.setVisible(false);
                prPythonHLBtn.setVisible(false);
                sphinxBtn.setVisible(false);
                break;
            case PogoConst.Python:
                makefileBtn.setVisible(false);
                cMakeListsBtn.setVisible(false);
                windowsLabel.setVisible(false);
                linuxLabel.setVisible(false);
                vc10Btn.setVisible(false);
                vc12Btn.setVisible(false);
                javaProjectLabel.setVisible(false);
                projectBtn.setVisible(false);
                pyHlProjectBtn.setVisible(false);
                prPythonHLBtn.setVisible(false);
                sphinxBtn.setVisible(false);
                break;
            case PogoConst.PythonHL:
                makefileBtn.setVisible(false);
                cMakeListsBtn.setVisible(false);
                windowsLabel.setVisible(false);
                linuxLabel.setVisible(false);
                vc10Btn.setVisible(false);
                vc12Btn.setVisible(false);
                javaProjectLabel.setVisible(false);
                projectBtn.setVisible(false);
                pyHlProjectBtn.setVisible(true);
                prPythonHLBtn.setVisible(true);
                sphinxBtn.setVisible(true);
                break;
        }


        boolean isAbstract = devclass.checkIfAbstractClass();
        if (isAbstract)
            warningLabel.setText(devclass.getPogoDeviceClass().getName() + warningLabel.getText());
        else
            warningPanel.setVisible(false);

        pack();
        setVisible(true);
        return returnStatus;
    }

    //======================================================
    //======================================================
    public int showDialog(PogoMultiClasses classes) {
        mode = PogoConst.MULTI_CLASS;
        String path = classes.getSourcePath();
        if (path == null || !new File(path).exists())
            path = MultiClassesPanel.homeDir;
        outPathText.setText(path);
        outPathText.setRequestFocusEnabled(true);
        outPathText.requestFocus();

        windowsLabel.setVisible(false);
        linuxLabel.setVisible(false);
        vc10Btn.setVisible(false);
        vc12Btn.setVisible(false);
        docLabel.setVisible(false);
        htmlBtn.setVisible(false);
        makefileBtn.setVisible(true);
        cMakeListsBtn.setVisible(cmakeAvailable);
        warningPanel.setVisible(false);

        javaProjectLabel.setVisible(false);
        projectBtn.setVisible(false);
        pyHlProjectBtn.setVisible(false);
        prPythonHLBtn.setVisible(false);
        sphinxBtn.setVisible(false);

        pack();
        setVisible(true);
        return returnStatus;
    }

    //======================================================
    //======================================================
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    //======================================================
    //======================================================
    public String getPath() {
        return outPathText.getText();
    }

    //======================================================
    //======================================================
    public String getGenerated() {
        String generated = "";
        for (JRadioButton btn : radioButtons) {
            if (btn.isSelected())
                generated += btn.getText() + ",";
        }
        if (generated.length() == 0)
            return generated;
        else {    //	Remove last comma
            //System.out.println(generated.substring(0, generated.length()-1));
            return generated.substring(0, generated.length() - 1);
        }
    }

    //======================================================
    //======================================================
    public DeviceClass getDevClass() {
        //	Set the output path from JTextField
        deviceClass.getPogoDeviceClass().getDescription().setSourcePath(outPathText.getText());
        //	Set the list for generated files from radio buttons
        deviceClass.getPogoDeviceClass().getDescription().setFilestogenerate(getGenerated());
        return deviceClass;
    }
    //======================================================
    //======================================================


    //======================================================
    //======================================================
    private boolean mustBeOverWritten(String fileName) {
        String path = outPathText.getText();
        String fullName = path + "/" + fileName;
        File file = new File(fullName);

        if (file.exists()) {
            if (JOptionPane.showConfirmDialog(this,
                    fileName + " already exists !\n" +
                            "Overwrite it ?",
                    "Confirmation Window",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                return true;
            }
        }
        return false;
    }

    //======================================================
    //======================================================
    private int manageMakefile() {
        String makefile = "Makefile";
        String cMakeLists = "CMakeLists.txt";
        if (mode == PogoConst.MULTI_CLASS)
            makefile += ".multi";

        //  Check if Makefile or Win project must be overwritten
        boolean overwriteMakefile = false;
        boolean overwriteCMakeLists = false;
        boolean overwriteVC12 = false;
        boolean overwriteVC10 = false;
        boolean generate = false;
        if (makefileBtn.isSelected()) {
            overwriteMakefile = mustBeOverWritten(makefile);
        } else
        if (cMakeListsBtn.isSelected()) {
            overwriteCMakeLists = mustBeOverWritten(cMakeLists);
        } else
            generate = true;
        if (mode == PogoConst.SINGLE_CLASS && vc10Btn.isSelected()) {
            overwriteVC10 = mustBeOverWritten("vc10_proj");
        } else
            generate = true;
        if (mode == PogoConst.SINGLE_CLASS && vc12Btn.isSelected()) {
            overwriteVC12 = mustBeOverWritten("vc12_proj");
        } else
            generate = true;

        // Ask for additional files
        String path = outPathText.getText();
        if (mode == PogoConst.SINGLE_CLASS &&
                (generate || overwriteMakefile || overwriteCMakeLists ||
                        overwriteVC12 || overwriteVC10)) {
            String lang = deviceClass.getPogoDeviceClass().getDescription().getLanguage();
            EList<AdditionalFile> files = deviceClass.getPogoDeviceClass().getAdditionalFiles();
            AdditionalFilesDialog dlg =
                    new AdditionalFilesDialog(this, files, path, Utils.getFileExtension(lang));
            if (dlg.showDialog() == JOptionPane.CANCEL_OPTION)
                return JOptionPane.CANCEL_OPTION;
        }

        if (overwriteMakefile) {
            //  Rename Makefile to be overwritten by XTend
            File file = new File(path + "/" + makefile);
            if (!file.renameTo(new File(file.toString() + ".bck")))
                System.err.println("Cannot rename " + file);
        }
        if (overwriteCMakeLists) {
            //  Rename CMakeLists to be overwritten by XTend
            File file = new File(path + "/" + cMakeLists);
            if (!file.renameTo(new File(file.toString() + ".bck")))
                System.err.println("Cannot rename " + file);
        }
        if (overwriteVC12) {
            //  Rename vc12_proj to be overwritten by XTend
            File file = new File(path + "/vc12_proj");
            if (!file.renameTo(new File(file.toString() + ".bck")))
                System.err.println("Cannot rename " + file);
        }
        if (overwriteVC10) {
            //  Rename vc10_proj to be overwritten by XTend
            File file = new File(path + "/vc10_proj");
            if (!file.renameTo(new File(file.toString() + ".bck")))
                System.err.println("Cannot rename " + file);
        }
        return JOptionPane.OK_OPTION;
    }
    //======================================================
    //======================================================

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton cMakeListsBtn;
    private javax.swing.JRadioButton codeBtn;
    private javax.swing.JLabel docLabel;
    private javax.swing.JRadioButton htmlBtn;
    private javax.swing.JLabel javaProjectLabel;
    private javax.swing.JLabel linuxLabel;
    private javax.swing.JRadioButton makefileBtn;
    private javax.swing.JTextField outPathText;
    private javax.swing.JRadioButton prPythonHLBtn;
    private javax.swing.JRadioButton projectBtn;
    private javax.swing.JRadioButton pyHlProjectBtn;
    private javax.swing.JRadioButton sphinxBtn;
    private javax.swing.JRadioButton vc10Btn;
    private javax.swing.JRadioButton vc12Btn;
    private javax.swing.JLabel warningLabel;
    private javax.swing.JPanel warningPanel;
    private javax.swing.JLabel windowsLabel;
    private javax.swing.JRadioButton xmiBtn;
    // End of variables declaration//GEN-END:variables
}
