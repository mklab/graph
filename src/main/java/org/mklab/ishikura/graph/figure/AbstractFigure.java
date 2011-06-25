/*
 * Created on 2010/10/19
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.ishikura.graph.figure;

import java.text.MessageFormat;

import org.mklab.ishikura.graph.graphics.Color;
import org.mklab.ishikura.graph.graphics.Graphics;


/**
 * 図の基本機能を実装した抽象クラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public abstract class AbstractFigure implements Figure {

  /** 図のx座標です。 */
  private int x;
  /** 図のy座標です。 */
  private int y;
  /** 図の横幅です。 */
  private int width;
  /** 図の縦幅です。 */
  private int height;
  /** 図の状態が妥当であるかどうかです。 */
  private boolean valid = false;
  /** 親の図です。 */
  private Figure parent;
  /** 背景の塗りつぶし色です。nullの場合には塗りつぶしを行いません。 */
  private Color backgroundColor = null;
  /** 可視状態かどうかを表します。デフォルトでは可視です。 */
  private boolean visible = true;

  /**
   * {@inheritDoc}
   */
  @Override
  public final void validate(Graphics g) {
    if (isValid()) return;
    layout(g);
    this.valid = true;
  }

  /**
   * レイアウトを行います。
   * 
   * @param g グラフィックスコンテキスト
   */
  protected void layout(@SuppressWarnings("unused") Graphics g) {
    // do nothing
  }

  /**
   * 図の妥当性が損なわれたことを通知します。
   */
  protected final void invalidate() {
    this.valid = false;
  }

  /**
   * 図の内部状態の妥当性を検証します。
   * 
   * @return 妥当であるかどうか
   */
  protected final boolean isValid() {
    return this.valid;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setX(int x) {
    if (x < 0) throw new IllegalArgumentException();
    final int oldX = this.x;
    this.x = x;
    if (x != oldX) invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setY(int y) {
    if (y < 0) throw new IllegalArgumentException();
    final int oldY = this.y;
    this.y = y;
    if (y != oldY) invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setWidth(int width) {
    if (width < 0) throw new IllegalArgumentException();
    final int oldWidth = this.width;
    this.width = width;
    if (width != oldWidth) invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setHeight(int height) {
    if (height < 0) throw new IllegalArgumentException();
    final int oldHeight = this.height;
    this.height = height;
    if (height != oldHeight) invalidate();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setBounds(int x, int y, int width, int height) {
    setX(x);
    setY(y);
    setWidth(width);
    setHeight(height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setSize(int width, int height) {
    setWidth(width);
    setHeight(height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getX() {
    return this.x;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getY() {
    return this.y;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getWidth() {
    return this.width;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int getHeight() {
    return this.height;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("hiding")
  @Override
  public boolean contains(int x, int y) {
    if (0 <= x && x < this.width && y >= 0 && y < this.height) return true;
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void draw(Graphics g) {
    if (isVisible() == false) return;

    validate(g);

    g.translate(this.x, this.y);
    g.clipRect(0, 0, this.width, this.height);

    try {
      drawBackground(g);
      handleDraw(g);
    } finally {
      g.clearClip();
      g.translate(-this.x, -this.y);
    }
  }

  /**
   * 背景色を設定します。
   * 
   * @param backgroundColor 背景色
   */
  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  /**
   * 背景色を取得します。
   * 
   * @return 背景色
   */
  public Color getBackgroundColor() {
    return this.backgroundColor;
  }

  /**
   * 可視状態を設定します。
   * 
   * @param isVisible 可視状態
   */
  public void setVisible(boolean isVisible) {
    final boolean old = this.visible;
    this.visible = isVisible;
    if (old != isVisible) invalidate();
  }

  /**
   * この図が可視であるか調べます。
   * 
   * @return visible 可視かどうか
   */
  public boolean isVisible() {
    return this.visible;
  }

  /**
   * 背景の描画を行います。
   * 
   * @param g グラフィックスコンテキスト
   */
  private void drawBackground(Graphics g) {
    if (this.backgroundColor != null) {
      final Color old = g.getColor();
      g.setColor(this.backgroundColor);
      g.fillRect(0, 0, getWidth(), getHeight());
      if (old != null) g.setColor(old);
    }
  }

  /**
   * 描画処理を行います。
   * 
   * @param g グラフィックスコンテキスト
   */
  protected abstract void handleDraw(Graphics g);

  /**
   * {@inheritDoc}
   */
  @Override
  public final Figure getParent() {
    return this.parent;
  }

  /**
   * 親の図を設定します。
   * 
   * @param parent 親の図。
   * @throws NullPointerException nullが与えられた場合
   * @throws IllegalStateException 親がすでに設定されていた場合
   */
  protected final void setParent(Figure parent) {
    if (parent == null) throw new NullPointerException();
    if (this.parent != null) throw new IllegalStateException("parent was already set."); //$NON-NLS-1$
    this.parent = parent;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("boxing")
  @Override
  public String toString() {
    return MessageFormat.format("{0} [x={1}, y={2}, width={3}, height={4}]", getClass().getName(), this.x, this.y, this.width, this.height); //$NON-NLS-1$
  }

}
