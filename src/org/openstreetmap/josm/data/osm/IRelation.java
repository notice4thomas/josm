// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.data.osm;

/**
 * IRelation captures the common functions of {@link Relation} and {@link RelationData}.
 * @since 4098
 */
public interface IRelation extends IPrimitive {

    /**
     * Returns the number of members.
     * @return number of members
     */
    int getMembersCount();

    /**
     * Returns id of the member at given index.
     * @param idx member index
     * @return id of the member at given index
     */
    long getMemberId(int idx);

    /**
     * Returns role of the member at given index.
     * @param idx member index
     * @return role of the member at given index
     */
    String getRole(int idx);

    /**
     * Returns type of the member at given index.
     * @param idx member index
     * @return type of the member at given index
     */
    OsmPrimitiveType getMemberType(int idx);
}
