//package info.hylle.aistuff.utils;
//
//import info.hylle.aistuff.SimpleJOGL;
//
//import javax.swing.*;
//import javax.media.opengl.GLCanvas;
//import java.awt.*;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//
//public class InputHandler extends KeyAdapter {
//    private SimpleJOGL renderer;
//
//    public InputHandler(SimpleJOGL renderer, GLCanvas glDisplay) {
//        this.renderer = renderer;
//        glDisplay.addKeyListener(new KeyAdapter() {
//            public void keyPressed(KeyEvent e) {
//                if (e.getKeyChar() == KeyEvent.VK_L) {
//                }
//                if (e.getKeyChar() == KeyEvent.VK_PAGE_UP) {
//                }
//                if (e.getKeyChar() == KeyEvent.VK_PAGE_DOWN) {
//                }
//                if (e.getKeyChar() == KeyEvent.VK_DOWN) {
//                }
//                if (e.getKeyChar() == KeyEvent.VK_UP) {
//                }
//                if (e.getKeyChar() == KeyEvent.VK_LEFT) {
//                }
//                if (e.getKeyChar() == KeyEvent.VK_RIGHT) {
//                }
//            }
//        });
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "Toggle lighting");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "Switch texture filter");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_B, 0), "Toggle blending");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), "Zoom in");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0), "Zoom out");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "Rotate slower along X-axis");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "Rotate faster along X-axis");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Rotate slower along Y-axis");
////
////        glDisplay.registerKeyStrokeForHelp(
////                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Rotate faster along Y-axis");
//    }
//
//
//    public void keyPressed(KeyEvent e) {
//
//        processKeyEvent(e, true);
//    }
//
//    public void keyReleased(KeyEvent e) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_L:
////                renderer.toggleLighting();
//                break;
//            case KeyEvent.VK_F:
////                renderer.switchFilter();
//                break;
//            case KeyEvent.VK_B:
////                renderer.toggleBlending();
//                break;
//            default:
//                processKeyEvent(e, false);
//        }
//    }
//
//    private void processKeyEvent(KeyEvent e, boolean pressed) {
//        switch (e.getKeyCode()) {
//            case KeyEvent.VK_PAGE_UP:
//                renderer.zoomIn(pressed);
//                break;
//            case KeyEvent.VK_PAGE_DOWN:
//                renderer.zoomOut(pressed);
//                break;
//            case KeyEvent.VK_UP:
//                renderer.increaseXspeed(pressed);
//                break;
//            case KeyEvent.VK_DOWN:
//                renderer.decreaseXspeed(pressed);
//                break;
//            case KeyEvent.VK_RIGHT:
//                renderer.increaseYspeed(pressed);
//                break;
//            case KeyEvent.VK_LEFT:
//                renderer.decreaseYspeed(pressed);
//                break;
//        }
//    }
//}
