/*
 * Copyright (C) 2006-2014 Christopho, Solarus - http://www.solarus-games.org
 *
 * Solarus Quest Editor is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Solarus Quest Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.solarus.editor.gui.edit_entities;

import org.solarus.editor.*;
import org.solarus.editor.entities.*;
import org.solarus.editor.gui.*;
import org.solarus.editor.map_editor_actions.*;

/**
 * A component to edit a pickable item.
 */
public class EditPickableComponent extends EditEntityComponent {

    // specific fields of a pickable item
    private TreasureChooser treasureField;

    /**
     * Constructor.
     * @param map the map
     * @param entity the entity to edit
     */
    public EditPickableComponent(Map map, MapEntity entity) {
        super(map, entity);
    }

    /**
     * Creates the specific fields for this kind of entity.
     */
    protected void createSpecificFields() {

        // treasure
        treasureField = new TreasureChooser(false);
        addField("Treasure", treasureField);
    }

    /**
     * Updates the information displayed in the fields.
     */
    public void update() {
        super.update(); // update the common fields

        Pickable pickable = (Pickable) entity;

        treasureField.setTreasure(
                pickable.getStringProperty("treasure_name"),
                pickable.getIntegerProperty("treasure_variant"),
                pickable.getStringProperty("treasure_savegame_variable"));
    }

    /**
     * Returns the specific part of the action made on the entity.
     * @return the specific part of the action made on the entity
     */
    protected ActionEditEntitySpecific getSpecificAction() {

        String treasureName = treasureField.getTreasure().getItemName();
        Integer treasureVariant = treasureField.getTreasure().getVariant();
        String treasureSavegameVariable = treasureField.getTreasure().getSavegameVariable();

        return new ActionEditEntitySpecific(entity,
                treasureName,
                treasureVariant == null ? null : Integer.toString(treasureVariant),
                treasureSavegameVariable);
    }
}

