/*
 * Copyright 2011 Vaadin Ltd.
 *
 * Licensed under the GNU Affero General Public License, Version 3.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.graph.demo.neo4j;

import javax.servlet.ServletContext;

import org.neo4j.kernel.EmbeddedGraphDatabase;

import com.vaadin.Application;
import com.vaadin.graph.GraphExplorer;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.*;

public class Neo4JDemo extends Application {
    private static final long serialVersionUID = 1L;
    public static final String GRAPHDB = "graphdb";
    private Window window;

    @Override
    public void init() {
        ServletContext servletContext = ((WebApplicationContext) getContext()).getHttpSession().getServletContext();
        EmbeddedGraphDatabase graphdb = (EmbeddedGraphDatabase) servletContext.getAttribute(GRAPHDB);

        window = new Window("Graph Explorer demo");
        setMainWindow(window);

        Neo4JRepository repo = new Neo4JRepository(graphdb);
        GraphExplorer<Neo4JNode, Neo4JArc> graph = new GraphExplorer<Neo4JNode, Neo4JArc>(repo);
        window.addComponent(graph);

        VerticalLayout content = (VerticalLayout) window.getContent();
        content.setSizeFull();
        content.setExpandRatio(graph, 1);
    }
}
