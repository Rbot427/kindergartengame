// JComponentWithEvents.java
// By David Kosbie

/*
  To use the JComponentWithEvents class:
    1.  Create a subclass with this main method:
           public static void main(String[] args) { launch(500, 400); }
    2.  Place this file in the same directory as your subclass:
           JComponentWithEvents.class
        (You may need to compile it from JComponentWithEvents.java)
    3.  If this fails to play sounds, you may need to install
        a soundbank from here:
           http://java.sun.com/products/java-media/sound/soundbanks.html
*/

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import java.lang.reflect.*;

// Additional imports for sound player
import java.net.*;
import java.io.*;
import java.applet.*;
import javax.sound.midi.*;
import javax.sound.sampled.*;

public class JComponentWithEvents extends JComponent
                                  implements MouseListener,
                                             MouseMotionListener,
                                             KeyListener,
                                             ComponentListener,
                                             ActionListener, // for timer
                                             Runnable // for deferredDoStart
{

  //////////////////////////////////////
  // PUBLIC INTERFACE
  //////////////////////////////////////

  // Methods to override
  public void start() { }
  public void timerFired()  { }
  public void paint(Graphics2D page) { }
  public void mousePressed(int x, int y) { }
  public void mouseDragged(int x, int y) { }
  public void mouseReleased(int x, int y) { }
  public void mouseClicked(int x, int y, int clickCount) { }
  public void mouseMoved(int x, int y) { }
  public void mouseEntered(int x, int y) { }
  public void mouseExited(int x, int y) { }
  public void keyPressed(char key) { }
  public void keyReleased(char key) { }
  public void keyTyped(char key) { }
  public void componentResized() { }

  // constants
  public static final char UP = KeyEvent.VK_UP;
  public static final char DOWN = KeyEvent.VK_DOWN;
  public static final char LEFT = KeyEvent.VK_LEFT;
  public static final char RIGHT = KeyEvent.VK_RIGHT;
  public static final char DELETE = KeyEvent.VK_DELETE;
  public static final char HOME = KeyEvent.VK_HOME;
  public static final char PAGE_UP = KeyEvent.VK_PAGE_UP;
  public static final char PAGE_DOWN = KeyEvent.VK_PAGE_DOWN;
  public static final char END = KeyEvent.VK_END;
  public static final char SPACE = KeyEvent.VK_SPACE;
  public static final char BACK_SPACE = KeyEvent.VK_BACK_SPACE;
  public static final char ENTER = KeyEvent.VK_ENTER;
  public static final char ESCAPE = KeyEvent.VK_ESCAPE;
  public static final char SHIFT = KeyEvent.VK_SHIFT;
  public static final char CAPS_LOCK = KeyEvent.VK_CAPS_LOCK;
  public static final char CONTROL = KeyEvent.VK_CONTROL;
  public static final char ALT = KeyEvent.VK_ALT;
  public static final char F1  = KeyEvent.VK_F1,  F2  = KeyEvent.VK_F2,  F3  = KeyEvent.VK_F3,
                           F4  = KeyEvent.VK_F4,  F5  = KeyEvent.VK_F5,  F6  = KeyEvent.VK_F6,
                           F7  = KeyEvent.VK_F7,  F8  = KeyEvent.VK_F8,  F9  = KeyEvent.VK_F9,
                           F10 = KeyEvent.VK_F10, F11 = KeyEvent.VK_F11, F12 = KeyEvent.VK_F12;

  // Methods to use, but not to override:
  public void beep() { doBeep(); }
  public void exit() { doExit(); }
  public int getTimerDelay()         { return _timerDelay; }
  public void setTimerDelay(int delay){ doSetTimerDelay(delay); }
  public void stopTimer()             { doStopTimer(); }
  public boolean isPaused()           { return _paused; }
  public void setPaused(boolean paused) { _paused = paused; }
  public char getPauseKey()           { return _pauseKey; }
  public void setPauseKey(char c)     { _pauseKey = c; }
  public char getUnpauseKey()         { return _unpauseKey; }
  public void setUnpauseKey(char c)   { _unpauseKey = c; }
  public void setIgnoreModifierKeys(boolean b) { ignoreModifierKeys = b; }
  public void play(String location)   { doPlay(location, false); }
  public void loop(String location)   { doPlay(location, true); }
  public void stopSounds()            { doStopSounds(); }
  public MouseEvent getMouseEvent()         { return currentMouseEvent; }
  public KeyEvent   getKeyEvent()           { return currentKeyEvent; }

  //////////////////////////////////////
  /// Version and semi-auto-update manager
  //////////////////////////////////////
  
  // private constructor for version checker instance in another thread
  private static final int NO_REASON = 0;
  private static final int CHECK_VERSION_REASON = 1;
  private int privateConstructorReason = NO_REASON;
  private JComponentWithEvents(int privateConstructorReason) {
    this.privateConstructorReason = privateConstructorReason;
  }

  public static final int version = 4;

  public static final int getVersion() { return version; }

  private static boolean checkedVersion = false;
  
  private final void checkVersion() {
    if (checkedVersion) return;
    checkedVersion = true;
    new Thread(new JComponentWithEvents(CHECK_VERSION_REASON)).start();
  }
  
  private final void runVersionCheckerInstance() {
    try {
      /*String url = "http://kosbie.net/cmu/JComponentWithEvents/";
      Scanner scanner = null;
      try { scanner = new Scanner(new java.net.URL(url).openStream()); }
      catch (Exception e) { if (isApplet) return; }
      if (scanner == null)
        throw new RuntimeException("Cannot load version checker from " + url);
      int version = -1;
      while (scanner.hasNext()) {
        // contains one line:  version=23
        String s = scanner.next();
        if (s.startsWith("version=")) {
          s = s.substring("version=".length());
          // string can have trailing garbage, so we'll skip Integer.parseInt...
          int currentVersion = 0;
          for (int i=0; i<s.length() && Character.isDigit(s.charAt(i)); i++)
            currentVersion = 10*currentVersion + s.charAt(i) - '0';
          checkVersion(currentVersion);
          return;
        }
      }
      throw new RuntimeException("Cannot find version number in JComponentWithEventsVersion.html");*/
    }
    catch (Exception e) {
      String msg = "Error in JComponentWithEvent.checkVersion: \n  " + e;
      showMessageDialog(msg);
      System.err.println(msg);
      return;
    }
  }

  private void checkVersion(int currentVersion) {
    if (version == currentVersion) return;
    String infoUrl = "http://kosbie.net/cmu/JComponentWithEvents/";
    String msg =  "A different version of JComponentWithEvents is available.\n"
                   + "You are using version " + version + ".\n"
                   + "The current version is version " + currentVersion + ".\n"
                   + "Please see the following web page to download the current version:\n"
                   + "   " + infoUrl;
    System.err.println(msg);
    showUrlInBrowser(infoUrl);
    String prompt = msg + "\n\nProceed with installed version anyway?";
    if (!showYesNoDialog(prompt))
      exit();
  }

  private void showUrlInBrowser(String url) {
    if (applet != null)
      try { applet.getAppletContext().showDocument(new URL(url), "_blank"); }
      catch (Exception e) { e.printStackTrace(); }
    else {
      Process p = null;
      // windows 1
      if (exec("iexplore.exe",url) != null) return;
      // windows 2
      if (exec("\"C:\\Program Files\\Internet Explorer\\IEXPLORE.EXE\"",url) != null) return;
      // mac
      if (exec(new String[]{"open", "-a", "Firefox.app", url}) != null) return;
      // oh well...
    }
  }

  private static Process exec(String... cmdArray) {
    try {
      return Runtime.getRuntime().exec(cmdArray);
    }
    catch (Exception e) {
      return null;
    }

  }

  //////////////////////////////////////
  /// JOptionPane wrapper methods
  //////////////////////////////////////

  public void showMessageDialog(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  public int showOptionDialog(String prompt, String... options) {
    int result = -1;
    while (result < 0) {
      result = JOptionPane.showOptionDialog(this, prompt, "Please choose...", JOptionPane.DEFAULT_OPTION,
                                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      if (result < 0) beep();
    }
     return result;
  }

  public boolean showYesNoDialog(String prompt) {
    int result = -1;
    while (result < 0) {
      result = showOptionDialog(prompt, "Yes", "No");
      if (result < 0) beep();
    }
    return (result == 0);
  }

  public String showInputDialog(String prompt) {
    String result = null;
    while (result == null) {
      result = JOptionPane.showInputDialog(this, prompt);
      if (result == null) beep();
    }
    return result;
  }

  //////////////////////////////////////
  /// setLineThickness Helper method
  //////////////////////////////////////

  public static void setLineThickness(Graphics page, int thickness) {
    if (thickness < 0) thickness = 0;
    ((Graphics2D)page).setStroke(new BasicStroke(thickness));
  }

  //////////////////////////////////////
  /// drawCenteredString Helper methods
  //////////////////////////////////////

  public static int getStringWidth(Graphics page, Font f, String s) {
    // Find the size of string s in the font of the Graphics context "page"
    FontMetrics fm   = page.getFontMetrics(f);
    java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, page);
    return (int)Math.round(rect.getWidth());
  }

  public static int getStringHeight(Graphics page, Font f, String s) {
    // Find the size of string s in the font of the Graphics context "page"
    FontMetrics fm   = page.getFontMetrics(f);
    java.awt.geom.Rectangle2D rect = fm.getStringBounds(s, page);
    return (int)Math.round(rect.getHeight());
  }

  public static int getStringAscent(Graphics page, Font f, String s) {
    // Find the size of string s in the font of the Graphics context "page"
    FontMetrics fm   = page.getFontMetrics(f);
    return fm.getAscent();
  }

  public static void drawCenteredString(Graphics page, String s, int left, int top, int width, int height) {
    Font font = page.getFont();
    int textWidth  = getStringWidth(page,font,s);
    int textHeight = getStringHeight(page,font,s);
    int textAscent = getStringAscent(page,font,s);

    // Center text horizontally and vertically within provided rectangular bounds
    int textX = left + (width - textWidth)/2;
    int textY = top + (height - textHeight)/2 + textAscent;
    page.drawString(s, textX, textY);
  }

  //////////////////////////////////////
  /// drawImage and drawCenteredImage and Helper methods
  /////////////////////////////////////

  // Paint the image in the given file rotated and scaled
  public void drawImage(Graphics page, String filename, int left, int top, double scale, double radians) {
    Image image = getImageFromFile(filename);
    drawImage(page, image, left, top, scale, radians);
  }

  // Same as drawImage, only here we position the image by its center
  public void drawCenteredImage(Graphics page, String filename, int cx, int cy, double scale, double radians) {
    Image image = getImageFromFile(filename);
    drawCenteredImage(page, image, cx, cy, scale, radians);
  }

  // Paint the given image rotated and scaled with a top-left at x,y
  public void drawImage(Graphics page, Image image, int x, int y, double scale, double radians) {
    double imageWidth  = image.getWidth(null);
    double imageHeight = image.getHeight(null);
    Dimension newSize = getImageSize(image, scale, radians);
    java.awt.geom.AffineTransform transform = new java.awt.geom.AffineTransform();
    transform.translate(x+newSize.getWidth()/2,y+newSize.getHeight()/2); // last (not first!)
    transform.rotate(radians);
    transform.scale(scale,scale);
    transform.translate(-imageWidth/2, -imageHeight/2);  // first
    ((Graphics2D)page).drawImage(image,transform,null);
  }

  // Same as drawImage, only here we position the image by its center
  public void drawCenteredImage(Graphics page, Image image, int cx, int cy, double scale, double radians) {
    Dimension newSize = getImageSize(image, scale, radians);
    drawImage(page, image, cx - (int)(newSize.getWidth()/2), cy - (int)(newSize.getHeight()/2), scale, radians);
  }

  // get the size of this image after it has been scaled and rotated
  public Dimension getImageSize(String filename, double scale, double radians) {
    Image image = getImageFromFile(filename);
    return getImageSize(image, scale, radians);
  }

  // get the size of this image after it has been scaled and rotated
  public Dimension getImageSize(Image image, double scale, double radians) {
    double imageWidth  = image.getWidth(null);
    double imageHeight = image.getHeight(null);
    java.awt.geom.AffineTransform transform = new java.awt.geom.AffineTransform();
    transform.rotate(radians);
    transform.scale(scale,scale);
    transform.translate(-imageWidth/2, -imageHeight/2);  // first
    double[] x = { 0, imageWidth, imageWidth ,     0       };
    double[] y = { 0,     0     , imageHeight, imageHeight };
    double minx=0, maxx=0, miny=0, maxy=0;
    java.awt.geom.Point2D.Double src = new java.awt.geom.Point2D.Double();
    java.awt.geom.Point2D.Double dst = new java.awt.geom.Point2D.Double();
    for (int i=0; i<4; i++) {
      src.setLocation(x[i],y[i]);
      transform.transform(src,dst);
      if (i == 0) {
        minx = maxx = dst.getX();
        miny = maxy = dst.getY();
      }
      else {
        minx = Math.min(dst.getX(),minx);
        miny = Math.min(dst.getY(),miny);
        maxx = Math.max(dst.getX(),maxx);
        maxy = Math.max(dst.getY(),maxy);
      }
    }
    return new Dimension((int)(maxx-minx), (int)(maxy-miny));
  }

  // Map from filenames to stored images, so we do not have to
  // reload the image every time we draw it
  private static java.util.HashMap<String,Image> fileToImageMap =
         new java.util.HashMap<String,Image>();

  // Convert a jpg or png file into an awt Image (this is a quick way;
  // we really should use an ImageObserver or some other more reliable way).
  public Image getImageFromFile(String filename) {
    Image image = fileToImageMap.get(filename);
    if (image != null) return image;
    URL url = null;
    if (applet != null) {
      try {
        url = new URL(applet.getCodeBase(), filename);
        image = applet.getImage(url);
      } catch (Exception e) { throw new RuntimeException(""+e); }
    }
    else {
      // if (!(new java.io.File(filename)).exists())
      // throw new RuntimeException("File does not exist: " + filename);
      // image = new ImageIcon(filename).getImage();
      image = loadBufferedImage(filename);
    }
    fileToImageMap.put(filename, image);
    return image;
  }

  //////////////////////////////////////
  /// shapesIntersect Helper method
  //////////////////////////////////////

  public static boolean shapesIntersect(Shape s1, Shape s2) {
    java.awt.geom.Area area = new java.awt.geom.Area(s1);
    area.intersect(new java.awt.geom.Area(s2));
    return !area.isEmpty();
  }

  //////////////////////////////////////
  /// getResourceAsStream Helper method
  //////////////////////////////////////

  public InputStream getResourceAsStream(String path) {
    InputStream is = getClass().getResourceAsStream(path);
    if (is == null)
      throw new RuntimeException("File " + path + " does not exist");
    return is;
  }
              
  //////////////////////////////////////
  /// loadBufferedImage and saveBufferedImage Helper methods
  //////////////////////////////////////

  // Convert a jpg/png/etc file into an awt BufferedImage
  public BufferedImage loadBufferedImage(String filename) {
    InputStream is = getResourceAsStream(filename);
    try { return ImageIO.read(is); }
    catch (Exception e) { throw new RuntimeException(e); }
  }  

  // Save the BufferedImage into the given file -- the image type is determined
  // by the extension of the file (.jpg, .png, etc)
  public void saveBufferedImage(BufferedImage image, String filename) {
    int dotIndex = filename.lastIndexOf('.');
    if (dotIndex < 0) throw new RuntimeException("file must end in .jpg, .png, etc");
    String format = filename.substring(dotIndex+1);
    String[] legalFormats = ImageIO.getReaderFormatNames();
    boolean ok = false;
    for (String legalFormat : legalFormats) {
      if (legalFormat.equalsIgnoreCase(format)) {
        ok = true;
        break;
      }
    }
    if (!ok)
      throw new RuntimeException("Illegal format " + format + " not in " +
                                 java.util.Arrays.toString(legalFormats));
    try { ImageIO.write(image, format, new File(filename)); }
    catch (Exception e) { throw new RuntimeException(e); }
  }
  
  //////////////////////////////////////
  // PRIVATE IMPLEMENTATION
  //////////////////////////////////////

  private int _defaultWidth, _defaultHeight;
  private boolean _paused = false, _exited = false;
  private char _pauseKey = (char)0, _unpauseKey = 'p';

  public static synchronized JComponentWithEvents newInstance(Class c, int w, int h) {
    JComponentWithEvents result = null;
    if (JComponentWithEvents.width0 != -1)
        throw new RuntimeException("width0 is not -1");
    try {
        JComponentWithEvents.width0 = w;
        JComponentWithEvents.height0 = h;
        result = (JComponentWithEvents)c.newInstance();
    } catch (Exception e) {System.out.println("Err!");e.printStackTrace();}
    finally {
        JComponentWithEvents.width0 = -1;
        JComponentWithEvents.height0 = -1;
    }
    return result;
  }

  private static int width0 = -1, height0 = -1;

  public JComponentWithEvents() {
    if (width0 == -1) return;
    _defaultWidth = JComponentWithEvents.width0;
    _defaultHeight= JComponentWithEvents.height0;
    setPreferredSize(new Dimension(_defaultWidth,_defaultHeight));
  }

  private void deferredDoStart() { if (!started) { start(); repaint(); started = true; } }
  public void run() {
    if (privateConstructorReason == CHECK_VERSION_REASON)
      runVersionCheckerInstance();
    else
      deferredDoStart();
  }
  private void doStart() { SwingUtilities.invokeLater(this); }

  public int getWidth() {
    int w = super.getWidth();
    return ((w > 0) ? w : _defaultWidth);
  }

  public int getHeight() {
    int h = super.getHeight();
    return ((h > 0) ? h : _defaultHeight);
  }

  private boolean started = false; // set to true in start()
  private boolean isApplet = true; // set to false in launch()
  private JApplet applet = null;   // set to the applet in launchApplet
  private boolean timerStopped = false;

  // to prevent paint from being inadvertently overrided
  public void paint(Graphics page) {
    super.paint(page);
  }

  public void paintComponent(Graphics page) {
    if (timer == null) {
      timer = new javax.swing.Timer(_timerDelay, this);
      if (!timerStopped) timer.start();
    }
    if (!started) return;
    page.setColor(Color.white);
    page.fillRect(0,0,getWidth(),getHeight());
    page.setColor(Color.black);
    if (warningMessage != null) {
      page.setColor(Color.red);
      page.setFont(new Font("SansSerif",Font.BOLD,20));
      for (int i=0; i<warningMessage.length; i++)
        page.drawString(warningMessage[i], 20, 30+30*i);
    }
    else if (_paused || _exited) {
      page.setFont(new Font("SansSerif",Font.BOLD,32));
      drawCenteredString(page,(_exited ? "Exited!" : "Paused!"),0,0,getWidth(),getHeight());
    }
    else {
      HashMap<Field,Object> oldValuesMap = getNonLocalVariableValues();
      paint((Graphics2D)page);
      checkNonLocalVariableValues(oldValuesMap);
    }
  }

  private String[] warningMessage = null;

  private void setWarningMessage(String... msg) {
    warningMessage = msg;
    beep();
    repaint();
  }

  private HashMap<Field,Object> getNonLocalVariableValues() {
    try {
      HashMap<Field,Object> valuesMap = new HashMap<Field,Object>();
      for (Field field : this.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        valuesMap.put(field,get(this,field));
      }
      return valuesMap;
    } catch (Exception e) {
      // we're in an applet and cannot access fields due to security restrictions...
      return null;
    }
  }

  private void checkNonLocalVariableValues(HashMap<Field,Object> oldValuesMap) {
    if (oldValuesMap == null) return;
    for (Field field : this.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      Object oldValue = oldValuesMap.get(field);
      Object newValue = get(this,field);
      if ((oldValue != newValue) &&
          ((oldValue == null) || (!oldValue.equals(newValue))))
        setWarningMessage("Warning:  Field changed in paint method!",
                          "Field: " + field.getName(),
                          "   Changed from: " + oldValue,
                          "   Changed to: " + newValue,
                          "Fields should only be changed in event handlers",
                          "and never in paint methods!");
    }
  }

  private Object get(Object object, Field field) {
    field.setAccessible(true);
    Object value = null;
    try { value = field.get(object); } catch (Exception e) { }
    // System.out.println(field.getName() + ": " + value);
    return value;
 }

  private void doExit() {
    if (applet == null)
      System.exit(0);
    else {
      _exited = _paused = true;
      doStopSounds();
    }
  }

  private void doBeep() { Toolkit.getDefaultToolkit().beep(); }

  private void doSetTimerDelay(int millis) {
    timerStopped = false;
    _timerDelay = Math.max(1,millis);
    if (timer != null) {
      timer.setDelay(_timerDelay);
      timer.setInitialDelay(_timerDelay);
      timer.restart();
    }
  }

  private void doStopTimer() {
   timerStopped = true;
   if (timer != null) timer.stop();
  }

  private javax.swing.Timer timer;
  private int _timerDelay = 250;

  // ignore these
  public void componentShown(ComponentEvent e) { }
  public void componentHidden(ComponentEvent e) { }
  public void componentMoved(ComponentEvent e) { }

  public void componentResized(ComponentEvent e) {
    if (!started || _paused) return;
    componentResized();
    repaint();
  }

  public void actionPerformed(ActionEvent evt) {
    if (!started || _paused) return;
    timerFired();
    repaint();
  }

  private MouseEvent currentMouseEvent = null;
  private KeyEvent   currentKeyEvent = null;

  public void mousePressed(MouseEvent e) {
    if (!started || _paused) return;
    currentMouseEvent = e;
    mousePressed(e.getX(), e.getY());
    currentMouseEvent = null;
    repaint();
  }

  public void mouseReleased(MouseEvent e) {
    if (!started || _paused) return;
    currentMouseEvent = e;
    mouseReleased(e.getX(), e.getY());
    currentMouseEvent = null;
    repaint();
  }

  public void mouseDragged(MouseEvent e) {
    if (!started || _paused) return;
    currentMouseEvent = e;
    mouseDragged(e.getX(), e.getY());
    currentMouseEvent = null;
    repaint();
  }

  public void mouseEntered(MouseEvent e) {
    if (!started || _paused) return;
    currentMouseEvent = e;
    mouseEntered(e.getX(), e.getY());
    currentMouseEvent = null;
    repaint();
  }

  public void mouseExited(MouseEvent e) {
    if (!started || _paused) return;
    currentMouseEvent = e;
    mouseExited(e.getX(), e.getY());
    currentMouseEvent = e;
    repaint();
  }

  public void mouseClicked(MouseEvent e) {
    if (!started || _paused) return;
    currentMouseEvent = e;
    mouseClicked(e.getX(), e.getY(), e.getClickCount());
    currentMouseEvent = e;
    repaint();
  }

  public void mouseMoved(MouseEvent e) {
    if (!started || _paused) return;
    currentMouseEvent = e;
    mouseMoved(e.getX(), e.getY());
    currentMouseEvent = e;
    repaint();
  }

  public void keyReleased(KeyEvent e) {
    if (!started || _paused) return;
    char key = getKeyEventChar(e);
    if (key == (char)0) return;
    currentKeyEvent = e;
    keyReleased(key);
    currentKeyEvent = null;
    repaint();
  }

  public void keyTyped(KeyEvent e) {
    if (!started || _paused) return;
    char key = getKeyEventChar(e);
    if (key == (char)0) return;
    currentKeyEvent = e;
    keyTyped(key);
    currentKeyEvent = null;
    repaint();
  }

  public void keyPressed(KeyEvent e) {
    if (!started || _exited) return;
    char key = getKeyEventChar(e);
    if (key == (char)0) return;
    currentKeyEvent = e;
    if (!_paused && (key == _pauseKey)) {
      _paused = true;
      doPauseSounds();
    }
    else if (_paused && !_exited && (key == _unpauseKey)) {
      _paused = false;
      doUnpauseSounds();
    }
    else
      keyPressed(key);
    currentKeyEvent = null;
    repaint();
  }

  private boolean ignoreModifierKeys = true;

  private char getKeyEventChar(KeyEvent e) {
    int keyCode = e.getKeyCode();
    char keyChar = e.getKeyChar();
    char key = ((keyChar < (char)65535) ? keyChar : (char)keyCode);
    if (ignoreModifierKeys &&
        ((key == SHIFT) || (key == CAPS_LOCK) || (key == CONTROL) || (key == ALT)))
      key = (char)0;
    return key;
  }

  private void addEventListeners() {
    addMouseListener(this);
    addMouseMotionListener(this);
    addKeyListener(this);
    addComponentListener(this);
  }

  public static void launch() {
    launch(500,400); // default size
  }

  public static synchronized void launch(int width, int height) {
    try {
      Class c = null;
      StackTraceElement[] em = new Exception().getStackTrace();
      for (StackTraceElement ste : em) {
        Class nextC = Class.forName(ste.getClassName());
        if (JComponentWithEvents.class.isAssignableFrom(nextC))
          c = nextC;
        else
          break;
      }
      if (c == null) throw new RuntimeException("No main class");
      JComponentWithEvents comp = JComponentWithEvents.newInstance(c,width,height);
      comp.isApplet = false;
      JFrame frame = new JFrame(comp.getClass().getName());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel cp = new JPanel();
      cp.setLayout(new BorderLayout());
      cp.add(comp);
      frame.setContentPane(cp);
      // comp.setPreferredSize(new Dimension(width,height));
      frame.pack();
      frame.setVisible(true);
      comp.launchApplet(null);
    } catch (Exception e) {System.out.println("Err!");e.printStackTrace();}
  }

  public void launchApplet(JApplet applet) {
    // because when we are the JComponent of an applet, "launch" is not called
    try {
      checkVersion();
      this.applet = applet;
      addEventListeners();
      setFocusable(true);
      requestFocusInWindow(); // send key events to "comp"
      doStart();
    } catch (Exception e) {System.out.println("Err!");e.printStackTrace();}
  }

  //////////////////////////
  // MIDI support
  //////////////////////////

  private URL toUrl(String location) throws Exception {
    if (location == null) return null;
    if (location .startsWith("http")) return new URL(location);
    return new File(location).toURI().toURL();
  }

  private InputStream toInputStream(String location) throws Exception {
    byte[] data = fileCache.get(location);
    if (data == null) {
      InputStream is;
      if (location.startsWith("http"))
        is = new URL(location).openStream();
      // else if (applet != null)
      //   is = new URL(applet.getCodeBase(),location).openStream();
      else {
        // is = new FileInputStream(new File(location));
        is = getResourceAsStream(location);
      }
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];
      int len;
      while ((len = is.read(buf)) > 0)
        bos.write(buf, 0, len);
      data = bos.toByteArray();
      fileCache.put(location,data);
    }
    return new ByteArrayInputStream(data);
  }

  private java.util.HashMap<String,byte[]> fileCache = new java.util.HashMap<String,byte[]>();

  private boolean inSandbox() {
    SecurityManager mgr = System.getSecurityManager();
    if (mgr == null) return false;
    try { mgr.checkExit(-1); }
    catch (Exception e) { return true; }
    return false;
  }

  public boolean isApplet() {
    return inSandbox();
  }

  private void doPlay(String location, boolean loop) {
    if (location == null) { return; }
    String err = null;
    if (!location.endsWith("wav") && !location.endsWith("mid") && !location.endsWith("midi"))
      err = "Unknown file type: " + location;
    else {
      try {
        InputStream is = toInputStream(location);
        if (location.endsWith("wav"))
          doPlayWav(is,loop);
        else if (location.endsWith("midi") || location.endsWith("mid"))
          doPlayMidi(is,loop);
      }
      catch (Exception e) {
        err = e.getMessage();
        if (isApplet) {
          if (!badAppletLocations.contains(location))
            System.err.println("Applet cannot access: " + location);
          badAppletLocations.add(location);
        }
        else {
          e.printStackTrace();
          throw new RuntimeException(err);
        }
      }
    }
  }

  // so we only report bad locations once...
  private HashSet<String> badAppletLocations = new HashSet<String>();

  private void doStopSounds() {
    doStopWavSounds();
    doStopMidiSounds();
  }

  private void doPauseSounds() {
    doPauseWavSounds();
    doPauseMidiSounds();
  }

  private void doUnpauseSounds() {
    doUnpauseWavSounds();
    doUnpauseMidiSounds();
  }

  ///////////////////
  // For: midi files
  ///////////////////

  private Sequencer sequencer = null;

  private void doPlayMidi(InputStream is, boolean loop) {
    try {
      doStopMidiSounds();
      sequencer = MidiSystem.getSequencer();
      sequencer.setSequence(MidiSystem.getSequence(is));
      if (loop)
        sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
      sequencer.open();
      sequencer.start();
    } catch (Exception e) { midiError("" + e); }
  }

  private void midiError(String msg) {
      System.err.println("Midi error: " + msg);
      sequencer = null;
  }

  private void doStopMidiSounds() {
    try {
      if ((sequencer == null) || (!sequencer.isRunning())) return;
      sequencer.stop();
      sequencer.close();
    }
    catch (Exception e) { midiError("" + e); }
    sequencer = null;
  }

  private void doPauseMidiSounds() {
    try {
      if ((sequencer == null) || (!sequencer.isRunning())) return;
      sequencer.stop();
      // sequencer.close();
    }
    catch (Exception e) { midiError("" + e); }
    // sequencer = null;
  }

  private void doUnpauseMidiSounds() {
    try {
      if (sequencer == null) return;
      sequencer.start();
      // sequencer.close();
    }
    catch (Exception e) { midiError("" + e); }
    // sequencer = null;
  }

  ///////////////////////////////
  // For: aif, au, and wav files
  ///////////////////////////////

  private void doPlayWav(InputStream is, boolean loop) {
    try {
      AudioInputStream stream = AudioSystem.getAudioInputStream(is);
      AudioFormat format = stream.getFormat();

      // Create the clip
      DataLine.Info info = new DataLine.Info(
          Clip.class, stream.getFormat(), ((int)stream.getFrameLength()*format.getFrameSize()));
      Clip clip = (Clip) AudioSystem.getLine(info);

      // This method does not return until the audio file is completely loaded
      clip.open(stream);

      // Start playing
      clip.start();
    } catch (Exception e) { throw new RuntimeException("" + e); }
  }

  private java.util.ArrayList<Clip> wavClips = new java.util.ArrayList<Clip>();

  private void doPauseWavSounds() {
    // @TODO: this does not stop the wav sounds!
    for (Clip clip : wavClips) { clip.stop(); }
  }

  private void doUnpauseWavSounds() {
    for (Clip clip : wavClips) clip.start();
  }

  private void doStopWavSounds() {
    for (Clip clip : wavClips) clip.stop();
    wavClips.clear();
  }

  ///////////////////////////////
  // For testing purposes
  ///////////////////////////////

  public static void main(String[] args) {
    launch(500,300);
  }
}
