/*
 * jBrowserDriver (TM)
 * Copyright (C) 2014-2015 Machine Publishers, LLC
 * ops@machinepublishers.com | machinepublishers.com
 * Cincinnati, Ohio, USA
 *
 * You can redistribute this program and/or modify it under the terms of the
 * GNU Affero General Public License version 3 as published by the Free
 * Software Foundation. Additional permissions or commercial licensing may be
 * available--see LICENSE file or contact Machine Publishers, LLC for details.
 *
 * For general details about how to investigate and report license violations,
 * please see: https://www.gnu.org/licenses/gpl-violation.html
 * and email the author: ops@machinepublishers.com
 * Keep in mind that paying customers have more rights than the AGPL alone offers.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License version 3
 * for more details.
 */
package com.machinepublishers.jbrowserdriver;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

import org.openqa.selenium.Keys;

import com.machinepublishers.jbrowserdriver.Util.Sync;
import com.sun.glass.ui.Application;

class Robot {
  private static final Random rand = new Random();
  private static final com.sun.glass.ui.Robot robot;
  static {
    robot = Util.exec(new Sync<com.sun.glass.ui.Robot>() {
      public com.sun.glass.ui.Robot perform() {
        return Application.GetApplication().createRobot();
      }
    });
  }
  private static final AtomicLong latestThread = new AtomicLong();
  private static final AtomicLong curThread = new AtomicLong();
  private static final Map<Keys, Integer> keyConvert = new HashMap<Keys, Integer>();
  static {
    keyConvert.put(Keys.ADD, KeyEvent.VK_ADD);
    keyConvert.put(Keys.ALT, KeyEvent.VK_ALT);
    keyConvert.put(Keys.ARROW_DOWN, KeyEvent.VK_DOWN);
    keyConvert.put(Keys.ARROW_LEFT, KeyEvent.VK_LEFT);
    keyConvert.put(Keys.ARROW_RIGHT, KeyEvent.VK_RIGHT);
    keyConvert.put(Keys.ARROW_UP, KeyEvent.VK_UP);
    keyConvert.put(Keys.BACK_SPACE, KeyEvent.VK_BACK_SPACE);
    keyConvert.put(Keys.CANCEL, KeyEvent.VK_CANCEL);
    keyConvert.put(Keys.CLEAR, KeyEvent.VK_CLEAR);
    keyConvert.put(Keys.COMMAND, KeyEvent.VK_META);
    keyConvert.put(Keys.CONTROL, KeyEvent.VK_CONTROL);
    keyConvert.put(Keys.DECIMAL, KeyEvent.VK_DECIMAL);
    keyConvert.put(Keys.DELETE, KeyEvent.VK_DELETE);
    keyConvert.put(Keys.DIVIDE, KeyEvent.VK_DIVIDE);
    keyConvert.put(Keys.DOWN, KeyEvent.VK_DOWN);
    keyConvert.put(Keys.END, KeyEvent.VK_END);
    keyConvert.put(Keys.ENTER, KeyEvent.VK_ENTER);
    keyConvert.put(Keys.EQUALS, KeyEvent.VK_EQUALS);
    keyConvert.put(Keys.ESCAPE, KeyEvent.VK_ESCAPE);
    keyConvert.put(Keys.F1, KeyEvent.VK_F1);
    keyConvert.put(Keys.F10, KeyEvent.VK_F10);
    keyConvert.put(Keys.F11, KeyEvent.VK_F11);
    keyConvert.put(Keys.F12, KeyEvent.VK_F12);
    keyConvert.put(Keys.F2, KeyEvent.VK_F2);
    keyConvert.put(Keys.F3, KeyEvent.VK_F3);
    keyConvert.put(Keys.F4, KeyEvent.VK_F4);
    keyConvert.put(Keys.F5, KeyEvent.VK_F5);
    keyConvert.put(Keys.F6, KeyEvent.VK_F6);
    keyConvert.put(Keys.F7, KeyEvent.VK_F7);
    keyConvert.put(Keys.F8, KeyEvent.VK_F8);
    keyConvert.put(Keys.F9, KeyEvent.VK_F9);
    keyConvert.put(Keys.HELP, KeyEvent.VK_HELP);
    keyConvert.put(Keys.HOME, KeyEvent.VK_HOME);
    keyConvert.put(Keys.INSERT, KeyEvent.VK_INSERT);
    keyConvert.put(Keys.LEFT, KeyEvent.VK_LEFT);
    keyConvert.put(Keys.LEFT_ALT, KeyEvent.VK_ALT);
    keyConvert.put(Keys.LEFT_CONTROL, KeyEvent.VK_CONTROL);
    keyConvert.put(Keys.LEFT_SHIFT, KeyEvent.VK_SHIFT);
    keyConvert.put(Keys.META, KeyEvent.VK_META);
    keyConvert.put(Keys.MULTIPLY, KeyEvent.VK_MULTIPLY);
    keyConvert.put(Keys.NUMPAD0, KeyEvent.VK_NUMPAD0);
    keyConvert.put(Keys.NUMPAD1, KeyEvent.VK_NUMPAD1);
    keyConvert.put(Keys.NUMPAD2, KeyEvent.VK_NUMPAD2);
    keyConvert.put(Keys.NUMPAD3, KeyEvent.VK_NUMPAD3);
    keyConvert.put(Keys.NUMPAD4, KeyEvent.VK_NUMPAD4);
    keyConvert.put(Keys.NUMPAD5, KeyEvent.VK_NUMPAD5);
    keyConvert.put(Keys.NUMPAD6, KeyEvent.VK_NUMPAD6);
    keyConvert.put(Keys.NUMPAD7, KeyEvent.VK_NUMPAD7);
    keyConvert.put(Keys.NUMPAD8, KeyEvent.VK_NUMPAD8);
    keyConvert.put(Keys.NUMPAD9, KeyEvent.VK_NUMPAD9);
    keyConvert.put(Keys.PAGE_DOWN, KeyEvent.VK_PAGE_DOWN);
    keyConvert.put(Keys.PAGE_UP, KeyEvent.VK_PAGE_UP);
    keyConvert.put(Keys.PAUSE, KeyEvent.VK_PAUSE);
    keyConvert.put(Keys.RETURN, KeyEvent.VK_ENTER);
    keyConvert.put(Keys.RIGHT, KeyEvent.VK_RIGHT);
    keyConvert.put(Keys.SEMICOLON, KeyEvent.VK_SEMICOLON);
    keyConvert.put(Keys.SEPARATOR, KeyEvent.VK_SEPARATOR);
    keyConvert.put(Keys.SHIFT, KeyEvent.VK_SHIFT);
    keyConvert.put(Keys.SPACE, KeyEvent.VK_SPACE);
    keyConvert.put(Keys.SUBTRACT, KeyEvent.VK_SUBTRACT);
    keyConvert.put(Keys.TAB, KeyEvent.VK_TAB);
    keyConvert.put(Keys.UP, KeyEvent.VK_UP);
    keyConvert.put(Keys.NULL, -1);
  }
  private static final Map<String, int[]> keyMap = new HashMap<String, int[]>();
  static {
    keyMap.put("1", new int[] { KeyEvent.VK_1 });
    keyMap.put("2", new int[] { KeyEvent.VK_2 });
    keyMap.put("3", new int[] { KeyEvent.VK_3 });
    keyMap.put("4", new int[] { KeyEvent.VK_4 });
    keyMap.put("5", new int[] { KeyEvent.VK_5 });
    keyMap.put("6", new int[] { KeyEvent.VK_6 });
    keyMap.put("7", new int[] { KeyEvent.VK_7 });
    keyMap.put("8", new int[] { KeyEvent.VK_8 });
    keyMap.put("9", new int[] { KeyEvent.VK_9 });
    keyMap.put("0", new int[] { KeyEvent.VK_0 });
    keyMap.put("a", new int[] { KeyEvent.VK_A });
    keyMap.put("b", new int[] { KeyEvent.VK_B });
    keyMap.put("c", new int[] { KeyEvent.VK_C });
    keyMap.put("d", new int[] { KeyEvent.VK_D });
    keyMap.put("e", new int[] { KeyEvent.VK_E });
    keyMap.put("f", new int[] { KeyEvent.VK_F });
    keyMap.put("g", new int[] { KeyEvent.VK_G });
    keyMap.put("h", new int[] { KeyEvent.VK_H });
    keyMap.put("i", new int[] { KeyEvent.VK_I });
    keyMap.put("j", new int[] { KeyEvent.VK_J });
    keyMap.put("k", new int[] { KeyEvent.VK_K });
    keyMap.put("l", new int[] { KeyEvent.VK_L });
    keyMap.put("m", new int[] { KeyEvent.VK_M });
    keyMap.put("n", new int[] { KeyEvent.VK_N });
    keyMap.put("o", new int[] { KeyEvent.VK_O });
    keyMap.put("p", new int[] { KeyEvent.VK_P });
    keyMap.put("q", new int[] { KeyEvent.VK_Q });
    keyMap.put("r", new int[] { KeyEvent.VK_R });
    keyMap.put("s", new int[] { KeyEvent.VK_S });
    keyMap.put("t", new int[] { KeyEvent.VK_T });
    keyMap.put("u", new int[] { KeyEvent.VK_U });
    keyMap.put("v", new int[] { KeyEvent.VK_V });
    keyMap.put("w", new int[] { KeyEvent.VK_W });
    keyMap.put("x", new int[] { KeyEvent.VK_X });
    keyMap.put("y", new int[] { KeyEvent.VK_Y });
    keyMap.put("z", new int[] { KeyEvent.VK_Z });
    keyMap.put("A", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_A });
    keyMap.put("B", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_B });
    keyMap.put("C", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_C });
    keyMap.put("D", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_D });
    keyMap.put("E", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_E });
    keyMap.put("F", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_F });
    keyMap.put("G", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_G });
    keyMap.put("H", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_H });
    keyMap.put("I", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_I });
    keyMap.put("J", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_J });
    keyMap.put("K", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_K });
    keyMap.put("L", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_L });
    keyMap.put("M", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_M });
    keyMap.put("N", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_N });
    keyMap.put("O", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_O });
    keyMap.put("P", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_P });
    keyMap.put("Q", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_Q });
    keyMap.put("R", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_R });
    keyMap.put("S", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_S });
    keyMap.put("T", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_T });
    keyMap.put("U", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_U });
    keyMap.put("V", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_V });
    keyMap.put("W", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_W });
    keyMap.put("X", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_X });
    keyMap.put("Y", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_Y });
    keyMap.put("Z", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_Z });
    keyMap.put("`", new int[] { KeyEvent.VK_BACK_QUOTE });
    keyMap.put("-", new int[] { KeyEvent.VK_MINUS });
    keyMap.put("=", new int[] { KeyEvent.VK_EQUALS });
    keyMap.put("[", new int[] { KeyEvent.VK_OPEN_BRACKET });
    keyMap.put("]", new int[] { KeyEvent.VK_CLOSE_BRACKET });
    keyMap.put("\\", new int[] { KeyEvent.VK_BACK_SLASH });
    keyMap.put(";", new int[] { KeyEvent.VK_SEMICOLON });
    keyMap.put("'", new int[] { KeyEvent.VK_QUOTE });
    keyMap.put(",", new int[] { KeyEvent.VK_COMMA });
    keyMap.put(".", new int[] { KeyEvent.VK_PERIOD });
    keyMap.put("/", new int[] { KeyEvent.VK_SLASH });
    keyMap.put("~", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE });
    keyMap.put("_", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS });
    keyMap.put("+", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS });
    keyMap.put("{", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET });
    keyMap.put("}", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET });
    keyMap.put("|", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH });
    keyMap.put(":", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON });
    keyMap.put("\"", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE });
    keyMap.put("<", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA });
    keyMap.put(">", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD });
    keyMap.put("?", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH });
    keyMap.put("!", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_1 });
    keyMap.put("@", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_2 });
    keyMap.put("#", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_3 });
    keyMap.put("$", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_4 });
    keyMap.put("%", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_5 });
    keyMap.put("^", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_6 });
    keyMap.put("&", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_7 });
    keyMap.put("*", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_8 });
    keyMap.put("(", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_9 });
    keyMap.put(")", new int[] { KeyEvent.VK_SHIFT, KeyEvent.VK_0 });
    keyMap.put("\t", new int[] { KeyEvent.VK_TAB });
    keyMap.put("\n", new int[] { KeyEvent.VK_ENTER });
    keyMap.put(" ", new int[] { KeyEvent.VK_SPACE });
  }

  static enum MouseButton {
    LEFT(com.sun.glass.ui.Robot.MOUSE_LEFT_BTN),
    MIDDLE(com.sun.glass.ui.Robot.MOUSE_MIDDLE_BTN),
    RIGHT(com.sun.glass.ui.Robot.MOUSE_RIGHT_BTN);
    private final int value;

    MouseButton(int value) {
      this.value = value;
    }

    int getValue() {
      return value;
    }
  }

  private final Stage stage;

  Robot(Stage stage) {
    this.stage = stage;
  }

  private static int[] convertKey(int codePoint, boolean pressAndRelease) {
    char[] chars = Character.toChars(codePoint);
    if (chars.length == 1) {
      Keys key = Keys.getKeyFromUnicode(chars[0]);
      if (key != null) {
        Integer mapping = keyConvert.get(key);
        if (mapping != null) {
          return new int[] { mapping };
        }
      }
    }
    String str = new String(new int[] { codePoint }, 0, 1);
    int[] mapping = keyMap.get(str);
    return mapping == null ?
        (pressAndRelease ? toClipboard(str)
            : new int[] { KeyEvent.getExtendedKeyCodeForChar(codePoint) })
        : mapping;
  }

  private static int[] toClipboard(String str) {
    ClipboardContent content = new ClipboardContent();
    content.putString(str);
    Clipboard.getSystemClipboard().setContent(content);
    return new int[] { KeyEvent.VK_CONTROL, KeyEvent.VK_V };
  }

  private static boolean isChord(CharSequence charSequence) {
    int[] codePoints = charSequence.codePoints().toArray();
    char[] chars = Character.toChars(codePoints[codePoints.length - 1]);
    if (chars.length == 1) {
      return Keys.NULL.equals(Keys.getKeyFromUnicode(chars[0]));
    }
    return false;
  }

  private void lock() {
    long myThread = latestThread.incrementAndGet();
    synchronized (curThread) {
      while (myThread != curThread.get() + 1) {
        try {
          curThread.wait();
        } catch (Exception e) {
          Logs.exception(e);
        }
      }
    }
  }

  private void unlock() {
    curThread.incrementAndGet();
    synchronized (curThread) {
      curThread.notifyAll();
    }
  }

  void keysPress(final CharSequence chars) {
    lock();
    try {
      final int[] codePoints = chars.codePoints().toArray();
      for (int i = 0; i < codePoints.length; i++) {
        final int cur = i;
        Util.exec(new Sync<Object>() {
          @Override
          public Object perform() {
            int[] converted = convertKey(codePoints[cur], false);
            for (int i = 0; i < converted.length; i++) {
              if (converted[i] != -1) {
                robot.keyPress(converted[i]);
              }
            }
            return null;
          }
        });
        pause();
      }
    } finally {
      unlock();
    }
  }

  void keysRelease(final CharSequence chars) {
    lock();
    try {
      final int[] codePoints = chars.codePoints().toArray();
      for (int i = 0; i < codePoints.length; i++) {
        final int cur = i;
        Util.exec(new Sync<Object>() {
          @Override
          public Object perform() {
            int[] converted = convertKey(codePoints[cur], false);
            for (int i = 0; i < converted.length; i++) {
              if (converted[i] != -1) {
                robot.keyRelease(converted[i]);
              }
            }
            return null;
          }
        });
        pause();
      }
    } finally {
      unlock();
    }
  }

  private static void pause() {
    Util.exec(new Sync<Object>() {
      @Override
      public Object perform() {
        try {
          Thread.sleep(30 + rand.nextInt(40));
        } catch (InterruptedException e) {}
        return null;
      }
    });
  }

  void keysType(final CharSequence... charsList) {
    StringJoiner joiner = new StringJoiner("");
    for (CharSequence chars : charsList) {
      joiner.add(chars);
    }
    final String toSend = joiner.toString();
    keysType(toSend);
  }

  void keysType(final CharSequence chars) {
    lock();
    try {
      final int[] codePoints = chars.codePoints().toArray();
      final boolean chord = isChord(chars);
      final List<Integer> toRelease = new ArrayList<Integer>();
      for (int i = 0; i < codePoints.length; i++) {
        final int cur = i;
        Util.exec(new Sync<Object>() {
          @Override
          public Object perform() {
            int[] converted = convertKey(codePoints[cur], !chord);
            for (int i = 0; i < converted.length; i++) {
              if (converted[i] != -1) {
                robot.keyPress(converted[i]);
              }
            }
            if (chord) {
              for (int i = 0; i < converted.length; i++) {
                if (converted[i] != -1) {
                  toRelease.add(converted[i]);
                }
              }
            } else {
              for (int i = converted.length - 1; i > -1; i--) {
                if (converted[i] != -1) {
                  robot.keyRelease(converted[i]);
                }
              }
            }
            return null;
          }
        });
        pause();
      }
      for (int i = toRelease.size() - 1; i > -1; i--) {
        final int key = toRelease.get(i);
        Util.exec(new Sync<Object>() {
          @Override
          public Object perform() {
            robot.keyRelease(key);
            return null;
          }
        });
        pause();
      }
    } finally {
      unlock();
    }
  }

  void mouseMove(final double pageX, final double pageY) {
    lock();
    try {
      Util.exec(new Sync<Object>() {
        @Override
        public Object perform() {
          robot.mouseMove((int) Math.rint(pageX + stage.getX() + stage.getScene().getX()),
              (int) Math.rint(pageY + stage.getY() + stage.getScene().getY()));
          return null;
        }
      });
      pause();
    } finally {
      unlock();
    }
  }

  void mouseClick(final MouseButton button) {
    mousePress(button);
    mouseRelease(button);
  }

  void mousePress(final MouseButton button) {
    lock();
    try {
      Util.exec(new Sync<Object>() {
        @Override
        public Object perform() {
          robot.mousePress(button.getValue());
          return null;
        }
      });
      pause();
    } finally {
      unlock();
    }
  }

  void mouseRelease(final MouseButton button) {
    lock();
    try {
      Util.exec(new Sync<Object>() {
        @Override
        public Object perform() {
          robot.mouseRelease(button.getValue());
          return null;
        }
      });
      pause();
    } finally {
      unlock();
    }
  }

  void mouseWheel(final int wheelAmt) {
    lock();
    try {
      Util.exec(new Sync<Object>() {
        @Override
        public Object perform() {
          robot.mouseWheel(wheelAmt);
          return null;
        }
      });
      pause();
    } finally {
      unlock();
    }
  }
}
