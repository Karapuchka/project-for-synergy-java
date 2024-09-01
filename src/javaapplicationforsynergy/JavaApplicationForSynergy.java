/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplicationforsynergy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author administrator
 */
public class JavaApplicationForSynergy {
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mainWindow();
    }
    
    // Главное окно
    public static void mainWindow(){
        JFrame mainWindow = new JFrame("Редактор Synergy");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Даём возможность польностью закрыть программу
        mainWindow.setSize(500,700); //Задаём ширину
        
        
        JMenuBar menuBar = new JMenuBar(); //Создаем меню
        
        JMenu menuFile = new JMenu("Файл"); //Выпадающее менб для работы с файлами
        JMenuItem menuAbout = new JMenuItem("О программе"); //Кнопка для открытия информации о программе
        
        //Событие по нажатию на кнопку. Вызывает новое окно с информацие о программе
        menuAbout.addActionListener((ActionEvent e) -> {
            aboutProgramm();
        });
       
        //Добавляем созданные элементы в меню
        menuBar.add(menuFile);
        menuBar.add(menuAbout);
        
         UIManager.put(
                 "FileChooser.saveButtonText", "Сохранить");
        UIManager.put(
                 "FileChooser.cancelButtonText", "Отмена");
        UIManager.put(
                 "FileChooser.fileNameLabelText", "Наименование файла");
        UIManager.put(
                 "FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put(
                 "FileChooser.lookInLabelText", "Директория");
        UIManager.put(
                 "FileChooser.saveInLabelText", "Сохранить в директории");
        UIManager.put(
                 "FileChooser.folderNameLabelText", "Путь директории");
                
        //Создаем дополнительные кнопки для меню работы с файлами
        JMenuItem menuFileOpen = new JMenuItem("Открыть");
        JMenuItem menuFileSave = new JMenuItem("Сохранить");
        
        menuFile.add(menuFileOpen);
        menuFile.add(menuFileSave);
        
        //Создаём панель для ввода текста
        JPanel mainPanel = new JPanel(new BorderLayout());
        JTextArea text = new JTextArea(); //Поле для ввода
        JScrollPane scroll = new JScrollPane(text); //Разрешаем скролл
        mainPanel.add(scroll, BorderLayout.CENTER);
        
        //Сохранение файла
        menuFileSave.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Текст (.txt)", "txt");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String saveText = text.getText();
                try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                    writer.write(saveText);
                    writer.flush();
                    JOptionPane.showMessageDialog(mainWindow, "Файл '" + fileChooser.getSelectedFile() + " сохранен.");
                } catch(IOException ioe) {
                    System.err.println(ioe);
                    System.exit(1);
                }
            }

        });
        
        //Открытие нового файла
        menuFileOpen.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Текст (.txt)", "txt");
            fileChooser.setFileFilter(filter);

            int ret = fileChooser.showDialog(null, "Открыть файл"); //Запись результата при открытии файла
            try{
                if(ret == JFileChooser.APPROVE_OPTION){
                    File doc = fileChooser.getSelectedFile();
                    BufferedReader reader = new BufferedReader(new FileReader(doc));
                    String line;
                    while ((line = reader.readLine()) != null){
                        if (!line.startsWith(">"))
                            {
                                text.append(line + "\n");
                            }
                    }
                }
            } catch(IOException ioe) {
                System.err.println(ioe);
                System.exit(1);
            }
        });
        
        mainWindow.getContentPane().add(BorderLayout.NORTH, menuBar);
        mainWindow.getContentPane().add(BorderLayout.CENTER, mainPanel);
        mainWindow.setVisible(true);
    }
    
    //Создание метода для открытия информации о программе
    public static void aboutProgramm(){
        JFrame aboutWindow = new JFrame();
        aboutWindow.setSize(350,120); //Задаём размер окна
        aboutWindow.setResizable(false); //Запрещяем изменять размер окна
        
        //Создание полей с информацией
        JLabel title = new JLabel("Информация о программе");
        JLabel programmVersion = new JLabel("Версия программы: 1.0.");
        JLabel programmAuthor = new JLabel("Разработчик: Кравец Артём.");
        JLabel programmContext = new JLabel("Программа разработана в образовательных целях.");
        
        //Создание разметки
        JPanel headerPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel footerPanel = new JPanel();
        
        //Добавление созданных полей в разметку
        headerPanel.add(title);
        mainPanel.add(programmVersion);
        mainPanel.add(programmAuthor);
        footerPanel.add(programmContext);
        
        //Распределение разметки по окну
        aboutWindow.getContentPane().add(BorderLayout.NORTH, headerPanel);
        aboutWindow.getContentPane().add(BorderLayout.CENTER, mainPanel);
        aboutWindow.getContentPane().add(BorderLayout.SOUTH, footerPanel);
        
        aboutWindow.setVisible(true);

    } 
}
