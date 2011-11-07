/*
 * Created on 2010/10/19
 * Copyright (C) 2010 Koga Laboratory. All rights reserved.
 *
 */
package org.mklab.graph.figure;

import java.util.ArrayList;
import java.util.List;

import org.mklab.abgr.Graphics;


/**
 * 複数の図のコンテナを表すクラスです。
 * 
 * @author Yuhi Ishikura
 * @version $Revision$, 2010/10/19
 */
public class ContainerFigureImpl extends AbstractFigure implements ContainerFigure {

  /** 子の図のリストです。 */
  private List<Figure> children;

  /**
   * 新しく生成された<code>ContainerFigureImpl</code>オブジェクトを初期化します。
   */
  public ContainerFigureImpl() {
    this.children = new ArrayList<Figure>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void add(Figure figure) {
    if (figure == null) throw new NullPointerException();
    if (figure.getParent() != null) throw new IllegalStateException("figure's parent was already set."); //$NON-NLS-1$

    this.children.add(figure);
    if (figure instanceof AbstractFigure) {
      ((AbstractFigure)figure).setParent(this);
    } else {
      throw new UnsupportedOperationException();
    }
    invalidate();
  }

  /**
   * 与えられた図をこの図から削除します。
   * 
   * @param figure 削除する図
   * @throws IllegalArgumentException 図が含まれていない場合
   */
  public final void remove(Figure figure) {
    if (this.children.remove(figure) == false) {
      throw new IllegalArgumentException("figure is not contained."); //$NON-NLS-1$
    }
    invalidate();
  }

  /**
   * すべての子の図を削除します。
   */
  public final void removeAll() {
    this.children.clear();
    invalidate();
  }

  /**
   * すべての子の図を取得します。
   * 
   * @return 子の図
   */
  public final Iterable<Figure> getChildren() {
    return this.children;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layout(Graphics g) {
    validateChildren(g);

    int maximumWidth = 0;
    int bottom = 0;
    for (Figure child : getChildren()) {
      if (child.getWidth() > maximumWidth) maximumWidth = child.getWidth();
      child.setY(bottom);
      bottom += child.getHeight();
      child.setX(0);
    }
  }

  /**
   * 子の図のレイアウトを行い、この図の横幅、縦幅を子に従い合わせます。
   * 
   * @param g グラフィックスコンテキスト
   */
  protected final void validateChildren(Graphics g) {
    for (final Figure child : this.children) {
      child.validate(g);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void pack() {
    int maxRight = 0;
    int maxBottom = 0;
    for (final Figure child : this.children) {
      final int right = child.getX() + child.getWidth();
      final int bottom = child.getY() + child.getHeight();
      if (maxRight < right) maxRight = right;
      if (maxBottom < bottom) maxBottom = bottom;
    }
    setWidth(maxRight);
    setHeight(maxBottom);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final List<Figure> getFiguresAt(int x, int y) {
    final List<Figure> list = new ArrayList<Figure>();
    for (final Figure child : this.children) {
      if (child.contains(x - child.getX(), y - child.getY()) == false) continue;

      if (child instanceof ContainerFigureImpl) {
        for (Figure childchild : ((ContainerFigure)child).getFiguresAt(x - child.getX(), y - child.getY())) {
          list.add(childchild);
        }
      }
      list.add(child);
    }
    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleDraw(Graphics g) {
    drawChildren(g);
  }

  /**
   * 子の図の描画を行います。
   * 
   * @param g グラフィックスコンテキスト
   */
  protected final void drawChildren(Graphics g) {
    for (Figure child : this.children) {
      child.draw(g);
    }
  }

}
